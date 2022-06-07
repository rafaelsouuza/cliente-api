package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String message) {
        super(message);
    }
}
