package it.uniroma3.catering.siw.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.siw.model.Ingrediente;
import it.uniroma3.catering.siw.model.User;


/**
 * Validator for Credentials
 */
@Component
public class IngredienteValidator implements Validator {
	
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;

	final Integer MAX_DESCRIPTION_LENGTH = 255;


    @Override
    public void validate(Object o, Errors errors) {
    	
        Ingrediente ingrediente = (Ingrediente) o;
        String nome = ingrediente.getNome().trim();
        String origine = ingrediente.getOrigine().trim();
        String descrizione = ingrediente.getDescrizione().trim();


		if (nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			errors.rejectValue("nome", "size");

		if (origine.length() > MAX_NAME_LENGTH)
			errors.rejectValue("origine", "size");

		if (descrizione.length() > MAX_DESCRIPTION_LENGTH)
			errors.rejectValue("descrizione", "size");


    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}