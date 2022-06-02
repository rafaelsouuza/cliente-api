package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Municipio;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validation.StatusValid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class MunicipioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoMunicipio;

    @Min(value = 0, message = "O campo codigo CÓDIGO UF deve ser um número positivo")
    private Integer codigoUF;

    @NotBlank(message = "O campo NOME é requerido")
    @Size(min = 3, max = 60, message = "O NOME deve ter entre 5 é 60 caracteres")
    private String nome;

    @StatusValid
    private Integer status;

    public MunicipioDto() {
    }

    public MunicipioDto(Integer codigoMunicipio, Integer codigoUF, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
    }

    public MunicipioDto(Municipio entity) {
        this.codigoMunicipio = entity.getCodigoMunicipio();
        this.codigoUF = entity.getCodigoUF().getCodigoUF();
        this.nome = entity.getNome();
        this.status = entity.getStatus();
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
