package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.StatusValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BairroDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoBairro;

    @NotNull(message = "Não foi possível incluir BAIRRO no banco de dados." +
            "<br>Motivo: o campo codigoMunicipio é obrigatório."
    )
    @Positive(message = "O campo codigo CÓDIGO MUNICÍPIO deve ser um número positivo")
    private Integer codigoMunicipio;

    @NotBlank(message = "Não foi possível incluir BAIRRO no banco de dados." +
            "<br>Motivo: o campo NOME é obrigatório."
    )
    @Size(min = 3, max = 60, message = "Não foi possível incluir BAIRRO no banco de dados." +
            "<br>Motivo: O campo NOME deve ter o tamanho entre 3 é 256 caracteres."
    )
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