package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Endereco;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Pessoa;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.validations.annotatios.StatusValid;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PessoaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoPessoa;

    @NotBlank(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo NOME é obrigatório."
    )
    @Size(min = 3, max = 256, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo NOME deve ter o tamanho entre 3 é 256 caracteres."
    )
    private String nome;

    @NotBlank(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo SOBRENOME é obrigatório."
    )
    @Size(min = 3, max = 256, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo SOBRENOME deve ter o tamanho entre 3 é 256 caracteres."
    )
    private String sobrenome;

    @NotNull(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo IDADE é obrigatório."
    )
    @Positive(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo IDADE não pode ser negativo."
    )
    @Min(value = 1)
    @Digits(integer = 3, fraction = 0, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo IDADE tem o limite máximo de 3 digitos."
    )
    private Integer idade;


    @NotBlank(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo LOGIN é obrigatório."
    )
    @Size(min = 3, max = 50, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo LOGIN deve ter o tamanho entre 3 é 50 caracteres."
    )
    private String login;

    @NotBlank(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo SENHA é obrigatório."
    )
    @Size(min = 3, max = 50, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo SENHA deve ter o tamanho entre 3 é 50 caracteres."
    )
    private String senha;

    @Valid
    private List<EnderecoDto> enderecos = new ArrayList<>();

    @NotNull(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo STATUS é obrigatório."
    )
    @StatusValid(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo STATUS aceita apenas o valor inteiro 1 - (ATIVADO) ou 2 - (DESATIVADO)."
    )
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
            endereco.setCodigoEndereco(e.getCodigoEndereco());
            endereco.setCodigoBairro(e.getCodigoBairro().getCodigoBairro());
            endereco.setCodigoPessoa(e.getCodigoPessoa().getCodigoPessoa());
            endereco.setNumero(e.getNumero());
            endereco.setNomeRua(e.getNomeRua());
            endereco.setNumero(e.getNumero());
            endereco.setComplemento(e.getComplemento());
            endereco.setCep(e.getCep());

            BairroDto bairro = new BairroDto(e.getCodigoBairro());
            endereco.setBairro(bairro);
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

    public void setEnderecos(List<EnderecoDto> enderecos) {
        this.enderecos = enderecos;
    }
}
