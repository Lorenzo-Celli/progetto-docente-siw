package it.uniroma3.catering.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("piatti", piattoService.findAll());
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


	@RequestMapping(value="/admin/piattoForm", method = RequestMethod.POST, params = "action=aggiungiNuovi")
	public String aggiungiNuoviIngredienti(Model model,
			@ModelAttribute("piatto") Piatto p) {
		Piatto pp = new Piatto();
		if (p.getId() != null) {
			pp = piattoService.findById(p.getId());
		}
		model.addAttribute("piatto",p);
		model.addAttribute("ingrediente" , new Ingrediente());
		model.addAttribute("ingredienti", pp.getIngredienti());
		piattoService.save(p);
		return "admin/ingredienteForm.html";

	}

	@RequestMapping(value = "/admin/piatto/doDelete/{id}", method = RequestMethod.GET) 
	private String deletePiatto(@PathVariable("id") Long id,Model model) {
		piattoService.delete(piattoService.findById(id));
		model.addAttribute("piatti", piattoService.findAll());
		return "admin/piatto.html";
	}


	@RequestMapping(value = "/admin/piatto/edit/{id}", method = RequestMethod.GET) 
	private String editPiatto(@PathVariable("id") Long id,Model model) {
		model.addAttribute("piatto", piattoService.findById(id));
		return "admin/piattoForm.html";
	}

}
