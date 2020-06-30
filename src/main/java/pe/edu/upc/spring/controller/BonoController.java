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

import pe.edu.upc.spring.model.Bono;
import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.model.Frecuencia;
import pe.edu.upc.spring.model.Tasa;
import pe.edu.upc.spring.model.Moneda;
import pe.edu.upc.spring.service.IBonoService;
import pe.edu.upc.spring.service.IEmpresaService;
import pe.edu.upc.spring.service.IFrecuenciaService;
import pe.edu.upc.spring.service.ITasaService;
import pe.edu.upc.spring.service.IMonedaService;

@Controller
@RequestMapping("/bono")
public class BonoController {
	
	@Autowired
	private IBonoService bService;
	
	@Autowired
	private IEmpresaService eService;
	
	@Autowired
	private IFrecuenciaService fService;
	
	@Autowired
	private ITasaService tService;
	
	@Autowired
	private IMonedaService mService;
	
	
	@RequestMapping("/bienvenido")
	public String irBonoBienvenido() {
		
		return "bienvenido";
	}
	
	@RequestMapping("/americano")
	public String americano(Map<String, Object> model) {
		model.put("listaBonos", bService.listar());
		return "americano";
	}
	
	@RequestMapping("/frances")
	public String frances(Map<String, Object> model) {
		model.put("listaBonos", bService.listar());
		return "frances";
	}
	
	@RequestMapping("/aleman")
	public String aleman(Map<String, Object> model) {
		model.put("listaBonos", bService.listar());
		return "aleman";
	}
	
	
	
	
	@RequestMapping("/")
	public String irBono(Map<String, Object>model) {
		model.put("listaBonos", bService.listar());
		return "listBono";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaEmpresas", eService.listar());
		model.addAttribute("listaFrecuencias", fService.listar());
		model.addAttribute("listaTasas", tService.listar());
		model.addAttribute("listaMonedas", mService.listar());
		model.addAttribute("bono", new Bono());
		model.addAttribute("empresa", new Empresa());
		model.addAttribute("frecuencia", new Frecuencia());
		model.addAttribute("tasa", new Tasa());
		model.addAttribute("moneda", new Moneda());
		return "bono";
			
	}
	

	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Bono objBono, BindingResult binRes, Model model)
	throws ParseException
	{
		if (binRes.hasErrors())
		{
			model.addAttribute("listaEmpresas", eService.listar());
			model.addAttribute("listaFrecuencias", fService.listar());
			model.addAttribute("listaTasas", tService.listar());
			model.addAttribute("listaMonedas", mService.listar());
			return "bono";
		}
		else 
		{
			boolean flag = bService.insertar(objBono);
			if (flag) {
				return "redirect:/bono/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/bono/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<Bono> objBono = bService.listarId(id);
		if (objBono == null ) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/bono/listar";
		}
		else {
			model.addAttribute("listaEmpresas", eService.listar());
			model.addAttribute("listaFrecuencias", fService.listar());
			model.addAttribute("listaTasas", tService.listar());
			model.addAttribute("listaMonedas", mService.listar());
			if (objBono.isPresent())
				objBono.ifPresent(o -> model.addAttribute("bono", o));
			
			return "bono";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				bService.eliminar(id);
				model.put("listaBonos", bService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaBonos", bService.listar());
		}
		return "listBono";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaBonos", bService.listar());
		return "listBono";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Bono Bono)
	throws ParseException{
		bService.listarId(Bono.getIdBono());
		return "listBono";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute Bono Bono)
	throws ParseException{
		List<Bono>listaBonos;
		Bono.setIdBono(Bono.getIdBono());
		listaBonos=bService.buscarId(Bono.getIdBono());
		
		if(listaBonos.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaBonos", listaBonos);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("bono",new Bono());
		return "buscar";
	}

}
