package it.uniroma3.catering.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.catering.siw.controller.validator.ChefValidator;
import it.uniroma3.catering.siw.model.Chef;
import it.uniroma3.catering.siw.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefValidator chefValidator;
	
	@Autowired
	private ChefService chefService;
	
	
	@RequestMapping(value="/admin/chef", method = RequestMethod.GET)
	public String getBuffets(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "admin/chef.html";
	}
	
	@RequestMapping(value="/admin/chefForm", method = RequestMethod.GET)
	public String addBuffets(Model model) {
		String cp = "chefForm";
		model.addAttribute("currentPath",cp);
		model.addAttribute("chef", new Chef());
		return "admin/chefForm.html";
	}
	

	@RequestMapping(value="/admin/chef/doDelete/{id}", method = RequestMethod.GET)
	public String doDelete(@PathVariable("id") Long id, Model model) {
		chefService.delete(chefService.findById(id));
		model.addAttribute("chefs", chefService.findAll());
		return "admin/chef.html";
	}
	
	
	@RequestMapping(value="/admin/chefForm", method = RequestMethod.POST, params = "action=salvaChefDalloChef")
	public String addBuffetss(@Valid @ModelAttribute("chef") Chef chef,
            				BindingResult bindingResult,
            				Model model) {
		
		this.chefValidator.validate(chef, bindingResult);
		
		
		if (!bindingResult.hasErrors()) {
			this.chefService.save(chef);
			model.addAttribute("chefs", chefService.findAll());
			return "admin/chef.html";
		}
		String cp = "chefForm";
		model.addAttribute("currentPath",cp);
		return "admin/chefForm.html";
	}
	



	
}
