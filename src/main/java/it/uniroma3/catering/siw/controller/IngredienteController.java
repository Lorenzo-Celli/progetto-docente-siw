package it.uniroma3.catering.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.catering.siw.model.Ingrediente;
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
	
	@RequestMapping(value="/admin/ingredienteForm", method = RequestMethod.POST, params = "action=keepAdding")
	public String addIngredients(Model model,
			@ModelAttribute("ingrediente") Ingrediente i) {
		
		ingredienteService.save(i);
		model.addAttribute("piatto", i.getPiatto());
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/ingredienteForm.html";

	}
	
	@RequestMapping(value="/admin/ingredienteForm", method = RequestMethod.POST, params = "action=finish")
	public String finishAdding(Model model,
			@ModelAttribute("ingrediente") Ingrediente i) {
		
		List<Ingrediente> ingredienti = new ArrayList<>();
		
		for (Ingrediente ii: ingredienteService.findAll()) {
			if (ii.getPiatto().equals(i.getPiatto())) {
				ingredienti.add(ii);
			}
		}
		ingredienteService.save(i);
		model.addAttribute("piatto", i.getPiatto());
		model.addAttribute("ingredienti", ingredienti);
		return "admin/piattoForm.html";

	}
	
}
