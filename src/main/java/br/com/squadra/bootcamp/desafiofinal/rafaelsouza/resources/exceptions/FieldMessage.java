package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String campo;
    private String messagem;

    public FieldMessage() {
    }

    public FieldMessage(String nomeCampo, String messagem) {
        this.campo = nomeCampo;
        this.messagem = messagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}
