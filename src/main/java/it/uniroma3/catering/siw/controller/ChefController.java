package it.uniroma3.catering.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.catering.siw.controller.validator.ChefValidator;
import it.uniroma3.catering.siw.model.Buffet;
import it.uniroma3.catering.siw.model.Chef;
import it.uniroma3.catering.siw.service.BuffetService;
import it.uniroma3.catering.siw.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefValidator chefValidator;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetService buffetService;
	
	@RequestMapping(value="/admin/chef", method = RequestMethod.GET)
	public String getBuffets(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "admin/chef.html";
	}
	
	@RequestMapping(value="/admin/chefForm", method = RequestMethod.GET)
	public String addBuffets(Model model) {
		model.addAttribute("chef", new Chef());
		return "admin/chefForm.html";
	}
	
	@RequestMapping(value="/admin/chef/doDelete/{id}", method = RequestMethod.GET)
	public String doDelete(@PathVariable("id") Long id, Model model) {
		model.addAttribute(this.chefService.findById(id));
		return "admin/doDelete.html";
	}
	
	@RequestMapping(value="/admin/confirmDelete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Long id, Model model) {
		this.chefService.delete(this.chefService.findById(id));
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "admin/chef.html";
	}
	
	
	@RequestMapping(value="/admin/chefForm", method = RequestMethod.POST)
	public String addBuffetss(@ModelAttribute("chef") Chef chef,
            				BindingResult bindingResult,
            				Model model) {
		
		this.chefValidator.validate(chef, bindingResult);
		
		
		if (!bindingResult.hasErrors()) {
			this.chefService.save(chef);
			List<Chef> chefs = chefService.findAll();
			model.addAttribute("chefs", chefs);
			return "admin/chef.html";
		}
		return "admin/chef.html";
	}
	
	@RequestMapping(value="/admin/chefForm", method = RequestMethod.POST, params = "action=salvaChef")
	public String adddChef(Model model,
						@ModelAttribute("chef") Chef c) {
		chefService.save(c);
		Buffet tmp = buffetService.findById(4L);
		model.addAttribute("buffet", tmp);
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", tmp.getChef());
		buffetService.delete(tmp);
		return "admin/buffetForm.html";
		
		
	}



	
}
