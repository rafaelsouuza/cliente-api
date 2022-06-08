package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQUENCE_ENDERECO"
    )
    @SequenceGenerator(
            name = "SEQUENCE_ENDERECO",
            allocationSize = 1
    )
    @Column(name = "CODIGO_ENDERECO")
    private Integer codigoEndereco;

    @ManyToOne
    @JoinColumn(name = "CODIGO_BAIRRO")
    private Bairro codigoBairro;

    @ManyToOne
    @JoinColumn(name = "CODIGO_PESSOA")
    private Pessoa codigoPessoa;

    @Column(name = "NOME_RUA")
    private String nomeRua;

    private String numero;
    private String complemento;
    private String cep;

    public Endereco() {
    }

    public Endereco(Integer codigoEndereco, Bairro codigoBairro, Pessoa codigoPessoa, String nomeRua,
                    String numero, String complemento, String cep) {
        this.codigoEndereco = codigoEndereco;
        this.codigoBairro = codigoBairro;
        this.codigoPessoa = codigoPessoa;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Integer getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Integer codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public Bairro getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Bairro codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public Pessoa getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Pessoa codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
