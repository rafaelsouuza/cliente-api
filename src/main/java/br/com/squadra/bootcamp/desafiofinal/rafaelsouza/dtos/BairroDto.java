package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validation.StatusValid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BairroDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoBairro;

    @Min(value = 0, message = "O campo codigo CÓDIGO MUNICIPIO deve ser um número positivo")
    private Integer codigoMunicipio;

    @NotBlank(message = "O campo NOME é requerido")
    @Size(min = 3, max = 256, message = "O NOME deve ter entre 3 é 256 caracteres")
    private String nome;

    @StatusValid
    private Integer status;

    public BairroDto() {
    }

    public BairroDto(Integer codigoBairro, Integer codigoMunicipio, String nome, Integer status) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }

    public BairroDto(Bairro entity) {
        this.codigoBairro = entity.getCodigoBairro();
        this.codigoMunicipio = entity.getCodigoMunicipio().getCodigoMunicipio();
        this.nome = entity.getNome();
        this.status = entity.getStatus();
    }

    public Integer getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Integer codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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