package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFUpdateDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.UFRespository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions.FieldMessage;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.UFUpdateValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UFUpdateValidator implements ConstraintValidator<UFUpdateValid, UFUpdateDto> {

    private UFRespository ufRespository;

    public UFUpdateValidator(UFRespository ufRespository) {
        this.ufRespository = ufRespository;
    }

    @Override
    public void initialize(UFUpdateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UFUpdateDto dto, ConstraintValidatorContext context) {

        List<FieldMessage> lista = new ArrayList<>();
        Optional<UF> validarSigla = ufRespository.bucarPelaSigla(dto.getSigla().toUpperCase());
        Optional<UF> validarNomeUF = ufRespository.bucarPeloNome(dto.getNome().toUpperCase());

        // Aqui testa as validação customozidas, acrescentando objetos FieldMessage à lista

        if (validarSigla.isPresent() && validarSigla.get().getCodigoUF() != dto.getCodigoUF()) {
            lista.add(new FieldMessage("sigla", "Já existe uma sigla com o nome "
                    + validarSigla.get().getSigla() + ". Você não pode atualizar o registro com uma sigla já existente."));
        }

        if (validarNomeUF.isPresent() && validarNomeUF.get().getCodigoUF() != dto.getCodigoUF()) {
            lista.add(new FieldMessage("nome", "Já existe um estado com o nome "
                    + validarNomeUF.get().getNome() + ". Você não pode atualizar o registro com um nome de estado já existente."));
        }

        // Caso for capturado algum erro esse laço vai inserir na lista de erros do Bean Validadtion
        for (FieldMessage elemento : lista) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(elemento.getMessagem())
                    .addPropertyNode(elemento.getCampo())
                    .addConstraintViolation();
        }

        return lista.isEmpty();
    }
}
