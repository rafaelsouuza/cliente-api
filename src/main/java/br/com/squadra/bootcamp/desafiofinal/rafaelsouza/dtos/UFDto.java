package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.StatusValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UFDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoUF;

    @NotBlank(message = "Não foi possível incluir UF no banco de dados." +
            "<br>Motivo: o campo NOME é obrigatório."
    )
    @Size(min = 3, max = 60, message = "Não foi possível incluir UF no banco de dados." +
            "<br>Motivo: O campo NOME deve ter o tamanho entre 3 é 60 caracteres.")
    @Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$",
            message = "Não foi possível incluir UF no banco de dados." +
            "<br>Motivo: O campo NOME aceita apenas letras."
    )
    private String nome;

    @NotBlank(message = "Não foi possível incluir UF no banco de dados." +
            "<br>Motivo: o campo SIGLA é obrigatório."
    )
    @Size(max = 2, message = "ão foi possível incluir UF no banco de dados." +
            "<br>Motivo: O campo SIGLA dever ter o tamanho máximo de 2 caracteres."
    )
    @Pattern(regexp = "[A-Za]+$", message = "Não foi possível incluir UF no banco de dados." +
            "<br>Motivo: O campo SIGLA aceita apenas letras."
    )
    private String sigla;

    @NotNull(message = "Não foi possível incluir UF no banco de dados." +
            "<br>Motivo: o campo STATUS é obrigatório."
    )
    @StatusValid(message = "Não foi possível incluir UF no banco de dados." +
            "<br>Motivo: O campo STATUS aceita apenas o valor inteiro 1 - (ATIVADO) ou 2 - (DESATIVADO)."
    )
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
