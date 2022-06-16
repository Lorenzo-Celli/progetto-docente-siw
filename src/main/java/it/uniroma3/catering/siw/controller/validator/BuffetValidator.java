package it.uniroma3.catering.siw.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.siw.model.Buffet;
import it.uniroma3.catering.siw.model.User;

@Component
public class BuffetValidator implements Validator {

    final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;
    
    final Integer MAX_DESCRIPTION_LENGTH = 255;

   
    @Override
    public void validate(Object o, Errors errors) {
        Buffet buffet= (Buffet) o;
        String nome = buffet.getNome().trim();
        String descrizione = buffet.getDescrizione().trim();

        if (nome.isEmpty())
            errors.rejectValue("nome", "required");
        else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("nome", "size");

        if (descrizione.length() > MAX_DESCRIPTION_LENGTH)
            errors.rejectValue("descrizione", "size");
        

        
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}

