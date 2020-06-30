package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.service.IEmpresaService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private IEmpresaService eService;
	
	@RequestMapping("/")
	public String irEmpresa(Map<String, Object>model) {
		model.put("listaEmpresas", eService.listar());
		return "listEmpresa";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresa";
			
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Empresa objEmpresa, BindingResult binRes, Model model)
	throws ParseException
	{
		if (binRes.hasErrors())
			return "empresa";
		else 
		{
			boolean flag = eService.insertar(objEmpresa);
			if (flag) {
				return "redirect:/empresa/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/empresa/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<Empresa> objEmpresa = eService.listarId(id);
		if (objEmpresa == null ) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/empresa/listar";
		}
		else {
			model.addAttribute("empresa", objEmpresa);
			return "empresa";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				eService.eliminar(id);
				model.put("listaEmpresas", eService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaEmpresas", eService.listar());
		}
		return "listEmpresa";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaEmpresas", eService.listar());
		return "listEmpresa";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Empresa Empresa)
	throws ParseException{
		eService.listarId(Empresa.getIdEmpresa());
		return "listEmpresa";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute Empresa Empresa)
	throws ParseException{
		List<Empresa>listaEmpresas;
		Empresa.setNombreEmpresa(Empresa.getNombreEmpresa());
		listaEmpresas=eService.buscarNombre(Empresa.getNombreEmpresa());
		
		if(listaEmpresas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaEmpresas", listaEmpresas);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("empresa",new Empresa());
		return "buscar";
	}

}
