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
import it.uniroma3.catering.siw.model.Piatto;
import it.uniroma3.catering.siw.service.IngredienteService;
import it.uniroma3.catering.siw.service.PiattoService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PiattoService piattoService;

	@RequestMapping(value="/admin/ingredienteForm", method = RequestMethod.POST)
	public String addPiatto(Model model,
			@ModelAttribute("ingrediente") Ingrediente i,
			@ModelAttribute("piatto") Piatto p) {
		
		if (p.getId() != null) {
			Piatto pi = piattoService.findById(p.getId());
			ingredienteService.save(i);
			pi.getIngredienti().add(i);
			
			model.addAttribute("ingredienti", pi.getIngredienti());
			model.addAttribute("piatto", pi);
			model.addAttribute("ingrediente", new Ingrediente());
			return "admin/piattoForm.html";
		}
		
		p.setIngredienti(new ArrayList<Ingrediente>());
		p.getIngredienti().add(i);
		piattoService.save(p);
		
		model.addAttribute("ingredienti", p.getIngredienti());
		model.addAttribute("piatto", p);
		model.addAttribute("ingrediente", new Ingrediente());
		
		return "admin/piattoForm.html";
	}

}
