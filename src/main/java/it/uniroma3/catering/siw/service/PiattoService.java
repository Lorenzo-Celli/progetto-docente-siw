package it.uniroma3.catering.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.catering.siw.model.Piatto;
import it.uniroma3.catering.siw.repository.PiattoRepository;

public class PiattoService {
	
	@Autowired
	private PiattoRepository pr;
	
	@Transactional
	public void save(Piatto b) {
		pr.save(b);
	}
	
	public Piatto findById(Long id) {
		return pr.findById(id).get();
	}
	
	public List<Piatto> findAll(){
		List<Piatto> Piatti = new ArrayList<>();
		for (Piatto b : pr.findAll()) {
			Piatti.add(b);
		}
		return Piatti;
	}
	
	@Transactional
	public void delete(Piatto Piatto) {
		pr.delete(Piatto);
	}
	
}
