package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Endereco;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PessoaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoPessoa;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String login;
    private String senha;
    private List<EnderecoDto> enderecos = new ArrayList<>();
    private Integer status;

    public PessoaDto() {
    }

    public PessoaDto(Integer codigoPessoa, String nome, String sobrenome, Integer idade, String login, String senha, Integer status) {
        this.codigoPessoa = codigoPessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }

    public PessoaDto(Pessoa entity) {
        this.codigoPessoa = entity.getCodigoPessoa();
        this.nome = entity.getNome();
        this.sobrenome = entity.getSobreNome();
        this.idade = entity.getIdade();
        this.login = entity.getLogin();
        this.senha = entity.getSenha();
        this.status = entity.getStatus();
    }

    public PessoaDto(Pessoa entity, List<Endereco> enderecos) {
        this(entity);

        for (Endereco e : enderecos) {
            EnderecoDto endereco = new EnderecoDto();
            endereco.setNumero(e.getNumero());
            endereco.setNomeRua(e.getNomeRua());
            endereco.setNumero(e.getNumero());
            endereco.setComplemento(e.getComplemento());
            endereco.setCep(e.getCep());
            this.enderecos.add(endereco);
        }
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public List<EnderecoDto> getEnderecos() {
        return enderecos;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
