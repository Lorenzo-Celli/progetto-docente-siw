package it.uniroma3.catering.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.catering.siw.controller.validator.BuffetValidator;
import it.uniroma3.catering.siw.controller.validator.ChefValidator;
import it.uniroma3.catering.siw.model.Buffet;
import it.uniroma3.catering.siw.model.Chef;
import it.uniroma3.catering.siw.model.Piatto;
import it.uniroma3.catering.siw.service.BuffetService;
import it.uniroma3.catering.siw.service.ChefService;
import it.uniroma3.catering.siw.service.PiattoService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private BuffetValidator buffetValidator;

	@Autowired
	private ChefService chefService;

	@Autowired
	private PiattoService piattoService;

	@Autowired
	private ChefValidator chefValidator;

	@RequestMapping(value="/admin/buffet", method = RequestMethod.GET)
	public String getBuffets(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "admin/buffet.html";
	}

	@RequestMapping(value="/admin/buffetForm", method = RequestMethod.GET)
	public String addBuffets(Model model) {
		model.addAttribute("piatti", piattoService.findAll());
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", new Chef());
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

	//riceve il post della form chefForm compilata con il nuovo chef da aggiungere
	@RequestMapping(value="/admin/buffetForm/createChef", method = RequestMethod.POST, params = "action=salvaChef")
	public String adddChef(Model model,
			@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult) {

		this.chefValidator.validate(chef, bindingResult);

		if (!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("buffet", new Buffet());
			model.addAttribute("chefs",chefService.findAll());
			model.addAttribute("currentChef", new Chef());
			model.addAttribute("piatti", piattoService.findAll());
			return "admin/buffetForm.html";
		}

		String cp = "buffetForm/createChef";
		model.addAttribute("currentPath", cp);
		return "admin/chefForm.html";

	}



	@RequestMapping(value="/admin/buffetForm", method = RequestMethod.POST, params = "action=savePiatto")
	public String salvaPiatto(Model model,
			@Valid @ModelAttribute("buffet") Buffet b, BindingResult bindingResult) {

		this.buffetValidator.validate(b, bindingResult);
		
		
		Piatto piattoCorrente = b.getPiatti().get(0);

		if (!bindingResult.hasErrors() && !(buffetService.containsPiatto(piattoCorrente, b))) {
			

			piattoCorrente.getBuffets().add(b);
			buffetService.save(b);
			piattoService.save(piattoCorrente);
			
			model.addAttribute("piatti", piattoService.findAll());
			model.addAttribute("buffet", b);
			model.addAttribute("chefs",chefService.findAll());
			return "admin/buffetForm.html";

		}
		
		model.addAttribute("buffet", b);
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("piatti", piattoService.findAll());

		return "admin/buffetForm.html";


	}

	@RequestMapping(value="/admin/buffetForm/piatti/{id}", method = RequestMethod.GET)
	public String visualizzaPiatti(Model model, @PathVariable("id") Long id) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("piatti", buffet.getPiatti());
		model.addAttribute("buffet", buffet);
		return "admin/piattiDelBuffet.html";
	}


	@RequestMapping(value="/admin/buffetForm", method = RequestMethod.POST ,  params = "action=saveAll")
	public String fineBuffet(Model model,@Valid @ModelAttribute("buffet") Buffet b, BindingResult bindingResult) {
	
		this.buffetValidator.validate(b, bindingResult);
		
		if (!bindingResult.hasErrors()){
			
			buffetService.save(b);
			return "redirect:/default";
			
		}
		
		model.addAttribute("buffet", b);
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("piatti", piattoService.findAll());

		return "admin/buffetForm.html";
		
	}

	@RequestMapping(value="/admin/buffetForm/return/{id}", method = RequestMethod.GET)
	public String addBuffets(Model model,@PathVariable("id") Long id) {
		model.addAttribute("piatti", piattoService.findAll());
		model.addAttribute("buffet", buffetService.findById(id));
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", buffetService.findById(id).getChef());
		return "admin/buffetForm.html";
	}

	@RequestMapping(value="/admin/buffet/doDelete/{id}", method = RequestMethod.GET)
	public String deleteBuffet(Model model,@PathVariable("id") Long id) {
		Buffet buffetCorrente = buffetService.findById(id);
		buffetCorrente.removeAllPiatti();
		buffetService.delete(buffetCorrente);
		model.addAttribute("buffets", buffetService.findAll());	
		return "admin/buffet.html";
	}

	@RequestMapping(value="/admin/buffet/edit/{id}", method = RequestMethod.GET)
	public String editBuffet(Model model,@PathVariable("id") Long id) {
		model.addAttribute("piatti", piattoService.findAll());
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("buffet", buffetService.findById(id));	
		return "admin/buffetForm.html";
	}


	/*+++++++++++++++++++++++++++++++++METODI LATO USER+++++++++++++++++++++++++++++++++++++++++*/


	@RequestMapping(value="/buffet/{id}", method = RequestMethod.GET)
	public String visualizzaBuffet(Model model, @PathVariable("id") Long id) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "buffet.html";
	}

}
