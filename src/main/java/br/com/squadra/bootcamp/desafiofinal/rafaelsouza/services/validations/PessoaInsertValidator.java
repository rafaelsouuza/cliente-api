package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.PessoaDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Pessoa;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.PessoaRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.PessoaInsertValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PessoaInsertValidator implements ConstraintValidator<PessoaInsertValid, PessoaDto> {

    private PessoaRepository pessoaRepository;

    public PessoaInsertValidator(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public void initialize(PessoaInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(PessoaDto dto, ConstraintValidatorContext context) {

        Optional<Pessoa> entidade = pessoaRepository.buscarPeloLogin(dto.getLogin());

        boolean valido = true;

        if (entidade.isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("O Login já existe.")
                    .addPropertyNode("login")
                    .addConstraintViolation();
            valido = false;
        }
        return valido;
    }
}
