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
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@RequestMapping(value = "/admin/piatto", method = RequestMethod.GET) 
	private String visualizzaPiatto(Model model) {
		List<Piatto> piatti = new ArrayList<>();
		piatti.add(new Piatto());
		model.addAttribute("piatti", piatti);
		return "admin/piatto.html";
	}
	
	@RequestMapping(value="/admin/piattoForm", method = RequestMethod.GET)
	public String addPiatto(Model model) {
		model.addAttribute("ingredientiTotali", ingredienteService.findAll());
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti", new Ingrediente());
		model.addAttribute("tmp", new Piatto());
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/piattoForm.html";
	}
	
	@RequestMapping(value="/admin/piattoForm", method = RequestMethod.POST, params = "action=saveIngrediente")
	public String adddChef(Model model,
			@ModelAttribute("piatto") Piatto p, @ModelAttribute("ingrediente") Ingrediente i) {
		
		i.setPiatto(p);
		ingredienteService.save(i);
		
		model.addAttribute(i);
		model.addAttribute("piatto", p);
		model.addAttribute("ingredientiTotali", ingredienteService.findAll());
		model.addAttribute("tmp", new Piatto());
		return "admin/piattoForm.html";


	}
	
	@RequestMapping(value="/admin/piattoForm", method = RequestMethod.POST, params = "action=aggiungiNuovi")
	public String aggiungiNuoviIngredienti(Model model,
			@ModelAttribute("piatto") Piatto p) {
		piattoService.save(p);
		model.addAttribute("piatto",p);
		model.addAttribute("ingrediente" , new Ingrediente());
		return "admin/ingredienteForm.html";
		
	}

}
