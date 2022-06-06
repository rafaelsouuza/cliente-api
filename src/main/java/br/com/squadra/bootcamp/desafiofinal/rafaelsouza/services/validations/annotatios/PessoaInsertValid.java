package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.PessoaInsertValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PessoaInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PessoaInsertValid {

    String message() default "O Login já existe. Por favor escolha outro Login.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
