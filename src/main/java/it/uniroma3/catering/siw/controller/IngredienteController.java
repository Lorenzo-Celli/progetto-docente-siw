package it.uniroma3.catering.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.catering.siw.model.Ingrediente;
import it.uniroma3.catering.siw.model.Piatto;
import it.uniroma3.catering.siw.service.IngredienteService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
//	@Autowired
//	private PiattoService piattoService;

	@RequestMapping(value="/admin/ingredienteForm", method = RequestMethod.GET)
	public String createPiatto(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/ingredienteForm";
		
	}
	
	@RequestMapping(value="/admin/ingredienteForm", method = RequestMethod.POST)
	public String adddChef(Model model,
			@ModelAttribute("ingrediente") Ingrediente i) {
		ingredienteService.save(i);
		List <Ingrediente> ingredientiTotali = ingredienteService.findAll(); 
		model.addAttribute("ingredientiTotali", ingredientiTotali);
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti", new Ingrediente());
		model.addAttribute("tmp", new Piatto());
		return "admin/piattoForm.html";


	}
	
}
