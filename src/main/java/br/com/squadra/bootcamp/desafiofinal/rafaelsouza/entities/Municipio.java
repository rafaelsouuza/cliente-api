package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities;

import javax.persistence.*;

@Entity
@Table(name = "TB_MUNICIPIO")
public class Municipio {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQUENCE_MUNICIPIO"
    )
    @SequenceGenerator(
            name = "SEQUENCE_MUNICIPIO",
            allocationSize = 1
    )
    @Column(name = "CODIGO_MUNICIPIO")
    private Integer codigoMunicipio;

    @ManyToOne
    @JoinColumn(name = "CODIGO_UF")
    private UF codigoUF;

    private String nome;
    private Integer status;

    public Municipio() {
    }

    public Municipio(Integer codigoMunicipio, UF codigoUF, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public UF getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(UF codigoUF) {
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
