package it.uniroma3.catering.siw.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.catering.siw.controller.validator.IngredienteValidator;
import it.uniroma3.catering.siw.model.Ingrediente;
import it.uniroma3.catering.siw.model.Piatto;
import it.uniroma3.catering.siw.service.IngredienteService;
import it.uniroma3.catering.siw.service.PiattoService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	@Autowired
	private PiattoService piattoService;


	@RequestMapping(value="/admin/ingredienteForm", method = RequestMethod.POST, params = "action=keepAdding")
	public String addIngredients(Model model,
			@ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult) {
		
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingredienti",ingredienteService.findByPiatto(ingrediente.getPiatto()));
			model.addAttribute("piatto", ingrediente.getPiatto());
			model.addAttribute("ingrediente", new Ingrediente());
			return "admin/ingredienteForm.html";
		}
		
		model.addAttribute("ingredienti",ingredienteService.findByPiatto(ingrediente.getPiatto()));
		model.addAttribute("piatto", ingrediente.getPiatto());
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/ingredienteForm.html";
	}
	
	@RequestMapping(value="/admin/ingredienteForm", method = RequestMethod.POST, params = "action=finish")
	public String finishAdding(Model model,
			@ModelAttribute("ingrediente") Ingrediente i) {
		
		ingredienteService.save(i); //da validare prima 
//		Piatto currentPiatto = piattoService.findById(i.getPiatto().getId());
//		currentPiatto.addIngredienti(ingredienteService.findByPiatto(currentPiatto));
//		piattoService.save(currentPiatto);
		return "redirect:/default";

	}
	
	@RequestMapping(value = "/admin/ingrediente/doDelete/{id}", method = RequestMethod.GET) 
	private String deleteIngrediente(@PathVariable("id") Long id,Model model) {
		Long idPiattoCorrente = ingredienteService.findById(id).getPiatto().getId();
		Piatto piattoCorrente = piattoService.findById(idPiattoCorrente);
		piattoCorrente.getIngredienti().remove(ingredienteService.findById(id));
		ingredienteService.delete(ingredienteService.findById(id));
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("piatto", piattoCorrente);
		model.addAttribute("ingredienti", piattoCorrente.getIngredienti());
		return "admin/ingredienteForm";
	}

	
}
