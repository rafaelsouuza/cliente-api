package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFInsertDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.UFRespository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions.FieldMessage;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.DataIntegrityException;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.UFInsertValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UFInsertValidator implements ConstraintValidator<UFInsertValid, String> {

    private UFRespository ufRespository;

    public UFInsertValidator(UFRespository ufRespository) {
        this.ufRespository = ufRespository;
    }

    @Override
    public void initialize(UFInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String sigla, ConstraintValidatorContext context) {

        Optional<UF> validarSigla = ufRespository.bucarPelaSigla(sigla.toUpperCase());

        return !validarSigla.isPresent();
    }
}
