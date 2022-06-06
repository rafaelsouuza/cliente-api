package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class EnderecoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoEndereco;

    @Positive(message = "Insira um valor positivo")
    private Integer codigoBairro;

    private Integer codigoPessoa;

    @NotBlank(message = "O campo NOME DA RUA é requerido")
    @Size(min = 3, max = 256, message = "O NOME DA RUA deve ter entre 3 é 256 caracteres")
    private String nomeRua;

    @NotBlank(message = "O campo NUMERO é requerido")
    @Size(min = 3, max = 10, message = "O NUMERO deve ter entre 5 é 256 caracteres")
    private String numero;

    private String complemento;

    @NotBlank(message = "O campo CEP é requerido")
    @Size(min = 3, max = 10, message = "O CEP deve ter até 10 caracteres")
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
