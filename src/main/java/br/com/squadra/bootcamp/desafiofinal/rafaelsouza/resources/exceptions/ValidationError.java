package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    List<FieldMessage> erros = new ArrayList<>();

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void adicionarErros(String campo, String mensagem) {
        erros.add(new FieldMessage(campo, mensagem));
    }
}
