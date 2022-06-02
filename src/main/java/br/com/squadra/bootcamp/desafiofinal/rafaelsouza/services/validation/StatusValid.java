package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validation;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.StatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusValid {

    String message() default "Status inválido. Por favor digite 1 no campo status.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
