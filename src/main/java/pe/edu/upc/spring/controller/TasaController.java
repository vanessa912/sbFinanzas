package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.spring.model.Tasa;
import pe.edu.upc.spring.service.ITasaService;

@Controller
@RequestMapping("/tasa")
public class TasaController {

	@Autowired
	private ITasaService tService;
	
	@RequestMapping("/bienvenido")
	public String irTasaBienvenido() {
		
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irFrecuencia(Map<String, Object>model) {
		model.put("listaTasas", tService.listar());
		return "listTasa";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("tasa", new Tasa());
		return "tasa";
			
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Tasa objTasa, BindingResult binRes, Model model)
	throws ParseException
	{
		if (binRes.hasErrors())
			return "tasa";
		else 
		{
			boolean flag = tService.insertar(objTasa);
			if (flag) {
				return "redirect:/tasa/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/tasa/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				tService.eliminar(id);
				model.put("listaTasas", tService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaTasas", tService.listar());
		}
		return "listTasa";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaTasas", tService.listar());
		return "listTasa";
	}
	
	
}
