package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.StatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusValid {

    String message() default "Status inválido. Por favor digite 1 ou 2 no campo status.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
