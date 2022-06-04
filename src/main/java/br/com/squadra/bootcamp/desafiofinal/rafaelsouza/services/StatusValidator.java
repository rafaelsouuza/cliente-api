package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validation.StatusValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<StatusValid, Integer> {

    @Override
    public void initialize(StatusValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return value == 1 || value == 2;
    }
}
