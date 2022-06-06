package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.PessoaDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Pessoa;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.PessoaRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.PessoaUpdateValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PessoaUpdateValidator implements ConstraintValidator<PessoaUpdateValid, PessoaDto> {

    private PessoaRepository pessoaRepository;

    public PessoaUpdateValidator(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public void initialize(PessoaUpdateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(PessoaDto dto, ConstraintValidatorContext context) {

        Optional<Pessoa> validarLogin = pessoaRepository.buscarPeloLogin(dto.getLogin());

        boolean valido = true;

        if (validarLogin.isPresent() && validarLogin.get().getCodigoPessoa() != dto.getCodigoPessoa()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("O Login já existe.")
                    .addPropertyNode("login")
                    .addConstraintViolation();
            valido = false;
        }
        return valido;
    }
}
