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

    @Override
    public void validate(Object o, Errors errors) {
        Ingrediente ingrediente = (Ingrediente) o;
        String nome = ingrediente.getNome();


        if (nome.isEmpty())
            errors.rejectValue("nome", "required");


    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}