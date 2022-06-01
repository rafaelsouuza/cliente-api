package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
