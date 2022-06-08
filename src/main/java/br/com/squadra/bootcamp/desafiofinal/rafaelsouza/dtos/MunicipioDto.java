package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Municipio;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.StatusValid;

import javax.validation.constraints.*;
import java.io.Serializable;

public class MunicipioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoMunicipio;

    @NotNull(message = "Não foi possível incluir MUNICÍPIO no banco de dados." +
            "<br>Motivo: o campo codigoUF é obrigatório."
    )
    @Positive(message = "O campo codigoUF deve ser um número positivo")
    private Integer codigoUF;

    @NotBlank(message = "Não foi possível incluir MUNICÍPIO no banco de dados." +
            "<br>Motivo: o campo NOME é obrigatório."
    )
    @Size(min = 3, max = 60, message = "Não foi possível incluir MUNICÍPIO no banco de dados." +
            "<br>Motivo: O campo NOME deve ter o tamanho entre 3 é 256 caracteres."
    )
    private String nome;

    @NotNull(message = "Não foi possível incluir MUNICÍPIO no banco de dados." +
            "<br>Motivo: o campo STATUS é obrigatório."
    )
    @StatusValid(message = "Não foi possível incluir MUNICÍPIO no banco de dados." +
            "<br>Motivo: O campo STATUS aceita apenas o valor inteiro 1 - (ATIVADO) ou 2 - (DESATIVADO)."
    )
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
