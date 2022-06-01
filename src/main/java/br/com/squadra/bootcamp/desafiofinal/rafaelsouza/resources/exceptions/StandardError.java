package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String mensagem;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
