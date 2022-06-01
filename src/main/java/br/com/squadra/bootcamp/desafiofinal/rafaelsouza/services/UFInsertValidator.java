package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFInsertDTO;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.UFRespository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions.FieldMessage;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validation.UFInsertValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UFInsertValidator implements ConstraintValidator<UFInsertValid, UFInsertDTO> {

    private UFRespository ufRespository;

    public UFInsertValidator(UFRespository ufRespository) {
        this.ufRespository = ufRespository;
    }

    @Override
    public void initialize(UFInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UFInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> lista = new ArrayList<>();

        Optional<UF> validarSigla = ufRespository.bucarPelaSigla(dto.getSigla());
        Optional<UF> validarNomeUF = ufRespository.bucarPeloNome(dto.getNome());

        // Aqui testa as validação customozidas, acrescentando objetos FieldMessage à lista

        if (validarSigla.isPresent()) {
            lista.add(new FieldMessage("sigla", "Sigla de estado já existente"));
        }

        if (validarNomeUF.isPresent()) {
            lista.add(new FieldMessage("nome", "Nome de estado ja existente"));
        }

        if (dto.getStatus() != 1) {
            lista.add(new FieldMessage("status", "O status aceita apenas 1 como valor"));
        }

        for (FieldMessage elemento : lista) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(elemento.getMessagem())
                    .addPropertyNode(elemento.getCampo())
                    .addConstraintViolation();
        }

        return lista.isEmpty();
    }
}
