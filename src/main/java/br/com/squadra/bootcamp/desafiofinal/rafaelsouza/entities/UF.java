package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_UF")
public class UF implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoUF;
    private String nome;
    private String sigla;
    private Integer status;

    public UF() {
    }

    public UF(Integer codigoUF, String nome, String sigla, Integer status) {
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.sigla = sigla;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UF)) return false;

        UF uf = (UF) o;

        return getCodigoUF().equals(uf.getCodigoUF());
    }

    @Override
    public int hashCode() {
        return getCodigoUF().hashCode();
    }
}
