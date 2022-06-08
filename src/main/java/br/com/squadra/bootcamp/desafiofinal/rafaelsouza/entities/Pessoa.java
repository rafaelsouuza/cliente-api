package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQUENCE_PESSOA"
    )
    @SequenceGenerator(
            name = "SEQUENCE_PESSOA",
            allocationSize = 1
    )
    @Column(name = "CODIGO_PESSOA")
    private Integer codigoPessoa;

    private String nome;

    @Column(name = "SOBRENOME")
    private String sobreNome;
    private Integer idade;
    private String login;
    private String senha;

    @OneToMany(mappedBy = "codigoPessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();
    private Integer status;

    public Pessoa() {
    }

    public Pessoa(Integer codigoPessoa, String nome, String sobreNome, Integer idade,
                  String login, String senha, Integer status) {
        this.codigoPessoa = codigoPessoa;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }

    public Integer getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Integer codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
