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

import pe.edu.upc.spring.model.Moneda;
import pe.edu.upc.spring.service.IMonedaService;

@Controller
@RequestMapping("/moneda")
public class MonedaController {
	
	@Autowired
	private IMonedaService mService;
	
	@RequestMapping("/")
	public String irMoneda(Map<String, Object>model) {
		model.put("listaMonedas", mService.listar());
		return "listMoneda";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("moneda", new Moneda());
		return "moneda";
			
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Moneda objMoneda, BindingResult binRes, Model model)
	throws ParseException
	{
		if (binRes.hasErrors())
			return "moneda";
		else 
		{
			boolean flag = mService.insertar(objMoneda);
			if (flag) {
				return "redirect:/moneda/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/moneda/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<Moneda> objMoneda = mService.listarId(id);
		if (objMoneda == null ) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/moneda/listar";
		}
		else {
			model.addAttribute("moneda", objMoneda);
			return "moneda";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				mService.eliminar(id);
				model.put("listaMonedas", mService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaMonedas", mService.listar());
		}
		return "listMoneda";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaMonedas", mService.listar());
		return "listMoneda";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Moneda Moneda)
	throws ParseException{
		mService.listarId(Moneda.getIdMoneda());
		return "listMoneda";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute Moneda Moneda)
	throws ParseException{
		List<Moneda>listaMonedas;
		Moneda.setTipoMoneda(Moneda.getTipoMoneda());
		listaMonedas=mService.buscarTipo(Moneda.getTipoMoneda());
		
		if(listaMonedas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMonedas", listaMonedas);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("moneda",new Moneda());
		return "buscar";
	}

}
