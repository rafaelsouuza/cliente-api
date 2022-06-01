package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> recursoNaoEncontrado(ResourceNotFoundException e) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setStatus(status.value());
        err.setMensagem(e.getMessage());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validacaoCampos(MethodArgumentNotValidException e) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError();
        err.setStatus(status.value());
        err.setMensagem("Erro na Validação dos campos");

        for (FieldError elemento : e.getBindingResult().getFieldErrors()) {
            err.adicionarErros(elemento.getField(), elemento.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }
}
