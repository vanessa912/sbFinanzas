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

import pe.edu.upc.spring.model.Frecuencia;
import pe.edu.upc.spring.service.IFrecuenciaService;

@Controller
@RequestMapping("/frecuencia")
public class FrecuenciaController {

	@Autowired
	private IFrecuenciaService fService;
	
	@RequestMapping("/bienvenido")
	public String irFrecuenciaBienvenido() {
		
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irFrecuencia(Map<String, Object>model) {
		model.put("listaFrecuencias", fService.listar());
		return "listFrecuencia";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("frecuencia", new Frecuencia());
		return "frecuencia";
			
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Frecuencia objFrecuencia, BindingResult binRes, Model model)
	throws ParseException
	{
		if (binRes.hasErrors())
			return "frecuencia";
		else 
		{
			boolean flag = fService.insertar(objFrecuencia);
			if (flag) {
				return "redirect:/frecuencia/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/frecuencia/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				fService.eliminar(id);
				model.put("listaFrecuencias", fService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaFrecuencias", fService.listar());
		}
		return "listFrecuencia";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaFrecuencias", fService.listar());
		return "listFrecuencia";
	}
	
	
}
