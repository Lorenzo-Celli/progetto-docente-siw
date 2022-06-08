package it.uniroma3.catering.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.siw.model.Ingrediente;
import it.uniroma3.catering.siw.model.Piatto;
import it.uniroma3.catering.siw.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ir;
	
	@Transactional
	public void save(Ingrediente b) {
		ir.save(b);
	}
	
	public Ingrediente findById(Long id) {
		return ir.findById(id).get();
	}
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> Ingredienti = new ArrayList<>();
		for (Ingrediente b : ir.findAll()) {
			Ingredienti.add(b);
		}
		return Ingredienti;
	}
	
	public List<Ingrediente> findByPiatto(Piatto p){
		List<Ingrediente> ingredienti = new ArrayList<>();
		List<Ingrediente> tutti = this.findAll();
		for (Ingrediente i : tutti) {
			if (i.getPiatto().equals(p)) {
				ingredienti.add(i);
			}
		}
		return ingredienti;
	}
	
	@Transactional
	public void delete(Ingrediente Ingrediente) {
		ir.delete(Ingrediente);
	}
	
}
