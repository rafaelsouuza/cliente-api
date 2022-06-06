package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.PessoaUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PessoaUpdateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PessoaUpdateValid {

    String message() default "O Login já existe. Por favor escolha outro Login.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
