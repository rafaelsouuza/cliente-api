package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validation.UFInsertValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

@UFInsertValid
public class UFDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoUF;

    @NotBlank(message = "O campo NOME é requerido")
    @Size(min = 3, max = 60, message = "O NOME deve ter entre 5 é 60 caracteres")
    private String nome;

    @NotBlank(message = "O campo SIGLA é requerido")
    @Size(max = 2, message = "O tamanho máximo é de 2 caracteres")
    private String sigla;

    @PositiveOrZero
    private Integer status;

    public UFDto() {
    }

    public UFDto(Integer codigoUF, String nome, String sigla, Integer status) {
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.sigla = sigla;
        this.status = status;
    }

    public UFDto(UF entity) {
        this.codigoUF = entity.getCodigoUF();
        this.nome = entity.getNome();
        this.sigla = entity.getSigla();
        this.status = entity.getStatus();
    }

    public Integer getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Integer codigoUF) {
        this.codigoUF = codigoUF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
