package it.uniroma3.catering.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.siw.model.Chef;
import it.uniroma3.catering.siw.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository cr;

	@Transactional
	public void save(Chef c) {
		cr.save(c);
	}
	
	public boolean exist(Chef chef) {
		for (Chef c : cr.findAll()) {
			if (c.equals(chef)) 
				return true;
		}
		return false;
	}
	
	public Chef findById(Long id) {
		return cr.findById(id).get();
	}

	public List<Chef> findAll(){
		List<Chef> Chefs = new ArrayList<>();
		for (Chef b : cr.findAll()) {
			Chefs.add(b);
		}
		return Chefs;
	}

	@Transactional
	public void delete(Chef Chef) {
		cr.delete(Chef);
	}

}
