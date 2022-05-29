package it.uniroma3.catering.siw.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.siw.model.Chef;
import it.uniroma3.catering.siw.model.User;

@Component
public class ChefValidator implements Validator {

    final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 1;

    @Override
    public void validate(Object o, Errors errors) {
        Chef chef = (Chef) o;
        String nome = chef.getNome().trim();
        String cognome = chef.getCognome().trim();
        String nazionalita = chef.getNazionalita().trim();

        if (nome.isEmpty())
            errors.rejectValue("nome", "required");
        else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("nome", "size");

        if (cognome.isEmpty())
            errors.rejectValue("cognome", "required");
        else if (cognome.length() < MIN_NAME_LENGTH || cognome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("cognome", "size");
        
        if (nazionalita.isEmpty())
            errors.rejectValue("nazionalita", "required");
        else if (cognome.length() < MIN_NAME_LENGTH || nazionalita.length() > MAX_NAME_LENGTH)
            errors.rejectValue("nazionalita", "size");
        
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}

