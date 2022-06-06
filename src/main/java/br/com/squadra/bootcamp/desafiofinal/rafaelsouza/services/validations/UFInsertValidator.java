package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFInsertDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.UFRespository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions.FieldMessage;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.UFInsertValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UFInsertValidator implements ConstraintValidator<UFInsertValid, UFInsertDto> {

    private UFRespository ufRespository;

    public UFInsertValidator(UFRespository ufRespository) {
        this.ufRespository = ufRespository;
    }

    @Override
    public void initialize(UFInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UFInsertDto dto, ConstraintValidatorContext context) {

        List<FieldMessage> lista = new ArrayList<>();

        Optional<UF> validarSigla = ufRespository.bucarPelaSigla(dto.getSigla().toUpperCase());
        Optional<UF> validarNomeUF = ufRespository.bucarPeloNome(dto.getNome().toUpperCase());

        // Aqui testa as validação customozidas, acrescentando objetos FieldMessage à lista

        if (validarSigla.isPresent()) {
            lista.add(new FieldMessage("sigla", "Já existe uma sigla com o nome "
                    + validarSigla.get().getSigla() + ". Você não pode cadastrar duas siglas com o mesmo nome."));
        }

        if (validarNomeUF.isPresent()) {
            lista.add(new FieldMessage("nome", "Já existe um estado com o nome "
                    + validarNomeUF.get().getNome() + ". Você não pode cadastrar dois estados com o mesmo nome."));
        }

        // Esse for vai inserir na lista de erros do Bean Validadtion
        for (FieldMessage elemento : lista) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(elemento.getMessagem())
                    .addPropertyNode(elemento.getCampo())
                    .addConstraintViolation();
        }

        return lista.isEmpty();
    }
}
