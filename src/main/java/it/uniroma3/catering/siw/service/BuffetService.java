package it.uniroma3.catering.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.siw.model.Buffet;
import it.uniroma3.catering.siw.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	private BuffetRepository br;
	
	@Transactional
	public void save(Buffet b) {
		br.save(b);
	}
	
	public Buffet findById(Long id) {
		return br.findById(id).get();
	}
	
	public List<Buffet> findAll(){
		List<Buffet> buffets = new ArrayList<>();
		for (Buffet b : br.findAll()) {
			buffets.add(b);
		}
		return buffets;
	}
	
	@Transactional
	public void delete(Buffet buffet) {
		br.delete(buffet);
	}
	
	
	@Transactional
	public void deleteById(Long id) {
		br.deleteById(id);
	}

}
