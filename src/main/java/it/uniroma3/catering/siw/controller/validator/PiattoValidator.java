package it.uniroma3.catering.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.siw.model.Piatto;
import it.uniroma3.catering.siw.model.User;
import it.uniroma3.catering.siw.service.PiattoService;

@Component
public class PiattoValidator implements Validator {

	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 1;

	final Integer MAX_DESCRIPTION_LENGTH = 255;

	@Autowired
	private PiattoService piattoService;

	@Override
	public void validate(Object o, Errors errors) {
		Piatto piatto= (Piatto) o;
		String nome = piatto.getNome().trim();
		String descrizione = piatto.getDescrizione().trim();

		if (nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			errors.rejectValue("nome", "size");


		if (descrizione.length() > MAX_DESCRIPTION_LENGTH)
			errors.rejectValue("descrizione", "size");

		if (piatto.getId() == null) {
			if (piattoService.exist(piatto))
				errors.reject("duplicate.piatto");
		}

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

}

