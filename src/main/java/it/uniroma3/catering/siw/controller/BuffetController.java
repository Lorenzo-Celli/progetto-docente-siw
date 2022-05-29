package it.uniroma3.catering.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.catering.siw.model.Buffet;
import it.uniroma3.catering.siw.model.Chef;
import it.uniroma3.catering.siw.service.BuffetService;
import it.uniroma3.catering.siw.service.ChefService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private ChefService chefService;

	@RequestMapping(value="/admin/buffet", method = RequestMethod.GET)
	public String getBuffets(Model model) {

		return "admin/buffet.html";
	}

	@RequestMapping(value="/admin/buffetForm", method = RequestMethod.GET)
	public String addBuffets(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", new Chef());
		return "admin/buffetForm.html";
	}


	@RequestMapping(value="/admin/buffetForm", method = RequestMethod.POST, params = "action=saveChef")
	public String addChef(Model model,
			@ModelAttribute("buffet") Buffet b) {
		Chef currentChef = b.getChef();
		model.addAttribute("buffet", b);
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", currentChef);
		return "admin/buffetForm.html";


	}

	@RequestMapping(value="/admin/buffetForm/createChef", method = RequestMethod.GET)
	public String createChef(Model model,
			@ModelAttribute("buffet") Buffet b) {
		String cp = "buffetForm/createChef";
		model.addAttribute("chef", new Chef());
		model.addAttribute("buffet", b);
		model.addAttribute("currentChef", b.getChef());
		model.addAttribute("currentPath", cp);
		return "admin/chefForm.html";


	}


	@RequestMapping(value="/admin/buffetForm/createChef", method = RequestMethod.POST, params = "action=saveChef")
	public String adddChef(Model model,
			@ModelAttribute("chef") Chef c) {
		chefService.save(c);
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", new Chef());
		return "admin/buffetForm.html";


	}



}
