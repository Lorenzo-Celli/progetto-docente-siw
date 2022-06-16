package it.uniroma3.catering.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.siw.model.Chef;
import it.uniroma3.catering.siw.model.User;
import it.uniroma3.catering.siw.service.ChefService;

@Component
public class ChefValidator implements Validator {

    final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;
    
    @Autowired
    private ChefService chefService;

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
        

        if (nazionalita.length() < MIN_NAME_LENGTH || nazionalita.length() > MAX_NAME_LENGTH)
            errors.rejectValue("nazionalita", "size");
        
        if (chefService.exist(chef)) {
        	errors.reject("duplicate.chef");
        }
        
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}

