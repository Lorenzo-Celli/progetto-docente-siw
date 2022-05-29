package it.uniroma3.catering.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.catering.siw.model.Ingrediente;
import it.uniroma3.catering.siw.model.Piatto;
import it.uniroma3.catering.siw.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@RequestMapping(value = "/admin/piatto", method = RequestMethod.GET) 
	private String visualizzaPiatto(Model model) {
		List<Piatto> piatti = new ArrayList<>();
		model.addAttribute("piatti", piatti);
		return "admin/piatto.html";
	}
	
	@RequestMapping(value="/admin/piattoForm", method = RequestMethod.GET)
	public String addPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/piattoForm.html";
	}

}
