package it.uniroma3.catering.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	private ChefService chefService;
	
	@Autowired
	private PiattoService piattoService;

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


	@RequestMapping(value="/admin/buffetForm/createChef", method = RequestMethod.POST, params = "action=salvaChef")
	public String adddChef(Model model,
			@ModelAttribute("chef") Chef c) {
		chefService.save(c);
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", new Chef());
		return "admin/buffetForm.html";


	}

	
	@RequestMapping(value="/admin/buffetForm", method = RequestMethod.POST, params = "action=saveBuffet")
	public String salvaBuffet(Model model,
			@ModelAttribute("buffet") Buffet b) {
		buffetService.save(b);
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", new Chef());
		return "admin/buffetForm.html";


	}
	
	@RequestMapping(value="/admin/buffetForm", method = RequestMethod.POST, params = "action=savePiatto")
	public String salvaPiatto(Model model,
			@ModelAttribute("buffet") Buffet b) {
		Piatto piattoCorrente = b.getPiatti().get(0);
		piattoCorrente.getBuffets().add(b);
		buffetService.save(b);
		piattoService.save(piattoCorrente);
		model.addAttribute("piatti", piattoService.findAll());
		model.addAttribute("buffet", b);
		model.addAttribute("chefs",chefService.findAll());
		model.addAttribute("currentChef", b.getChef());
		return "admin/buffetForm.html";


	}
	
	@RequestMapping(value="/admin/buffetForm/piatti/{id}", method = RequestMethod.GET)
	public String visualizzaPiatti(Model model, @PathVariable("id") Long id) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("piatti", buffet.getPiatti());
		model.addAttribute("buffet", buffet);
		return "admin/piattiDelBuffet.html";
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
	
	/*+++++++++++++++++++++++++++++++++METODI LATO USER+++++++++++++++++++++++++++++++++++++++++*/
	
	@RequestMapping(value="/buffet", method = RequestMethod.GET)
	public String visualizzaBuffets(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "elencoBuffets.html";
	}
	
	@RequestMapping(value="/buffet/{id}", method = RequestMethod.GET)
	public String visualizzaBuffet(Model model, @PathVariable("id") Long id) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "buffet.html";
	}

}
