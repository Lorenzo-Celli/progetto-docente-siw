package it.uniroma3.catering.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChefController {
	
	@RequestMapping(value="/admin/chef", method = RequestMethod.GET)
	public String getBuffets(Model model) {
		
		return "admin/buffet.html";
	}
	
	@RequestMapping(value="/admin/Form", method = RequestMethod.GET)
	public String addBuffets(Model model) {
		
		return "admin/buffetForm.html";
	}

	
}
