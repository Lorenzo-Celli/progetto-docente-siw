package it.uniroma3.catering.siw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Buffet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String descrizione;
	
	@ManyToOne()
	private Chef chef;
	
	@ManyToMany(mappedBy = "buffets")
	private List<Piatto> piatti;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}
	
	
	/*funzione fatta in questo modo poichè andando ad eliminare
	un piatto nella lista gli altri scalano e con un iterazione normale 
	il puntatore non funziona*/
	public void removeAllPiatti() {
		
		while(!this.getPiatti().isEmpty()) {
			this.getPiatti().get(0).getBuffets().remove(this);
			this.getPiatti().remove(0);
		}
	}
	

}
