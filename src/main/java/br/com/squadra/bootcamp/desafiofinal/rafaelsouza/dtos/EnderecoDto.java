package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Endereco;

import javax.validation.constraints.*;
import java.io.Serializable;

public class EnderecoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoEndereco;

    @NotNull(message = "Não foi possível incluir ENDEREÇO no banco de dados." +
            "<br>Motivo: o campo codigoBairro é obrigatório."
    )
    @Positive(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo codigoBairro não pode ser negativo."
    )
    @Min(value = 1)
    @Digits(integer = 9, fraction = 0, message = "Não foi possível incluir ENDEREÇO no banco de dados." +
            "<br>Motivo: o campo codigoBairro tem o limite máximo de 9 digitos."
    )
    private Integer codigoBairro;

    private Integer codigoPessoa;

    @NotBlank(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo nomeRua é obrigatório."
    )
    @Size(min = 3, max = 256, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo nomeRua deve ter o tamanho entre 3 é 256 caracteres."
    )
    private String nomeRua;

    @NotBlank(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo numero é obrigatório."
    )
    @Size(min = 1, max = 10, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo numero deve ter o tamanho entre 1 é 10 caracteres."
    )
    private String numero;

    @Size(max = 20, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo complemento deve ter o tamanho máximo de 20 caracteres."
    )
    private String complemento;

    @NotBlank(message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: o campo cep é obrigatório."
    )
    @Size(min = 1, max = 10, message = "Não foi possível incluir PESSOA no banco de dados." +
            "<br>Motivo: O campo cep deve ter o tamanho entre 1 é 10 caracteres."
    )
    private String cep;

    private BairroDto bairro;

    public EnderecoDto() {
    }

    public EnderecoDto(Integer codigoEndereco, Integer codigoBairro, Integer codigoPessoa,
                       String nomeRua, String numero, String complemento, String cep, BairroDto bairro) {
        this.codigoEndereco = codigoEndereco;
        this.codigoBairro = codigoBairro;
        this.codigoPessoa = codigoPessoa;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
    }

    public EnderecoDto(Endereco entity) {
        this.codigoEndereco = entity.getCodigoEndereco();
        this.codigoBairro = entity.getCodigoBairro().getCodigoBairro();
        this.codigoPessoa = entity.getCodigoPessoa().getCodigoPessoa();
        this.nomeRua = entity.getNomeRua();
        this.numero = entity.getNumero();
        this.complemento = entity.getComplemento();
        this.cep = entity.getCep();
    }

    public Integer getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Integer codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public Integer getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Integer codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public Integer getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Integer codigoPessoa) {
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

    public BairroDto getBairro() {
        return bairro;
    }

    public void setBairro(BairroDto bairro) {
        this.bairro = bairro;
    }
}
