package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.CodigoUFNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(CodigoUFNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> codigoUFNaoEncontrado(CodigoUFNaoEncontradoException e) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao err = new ErroPadrao();
        err.setStatus(status.value());
        err.setMensagem(e.getMessage());

        return ResponseEntity.status(status).body(err);
    }
}
