package it.uniroma3.catering.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.catering.siw.model.Ingrediente;
import it.uniroma3.catering.siw.repository.IngredienteRepository;

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
	
	@Transactional
	public void delete(Ingrediente Ingrediente) {
		ir.delete(Ingrediente);
	}
	
}
