package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.BairroDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.BairroGetDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.EnderecoDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.MunicipioDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.MunicipioGetDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.PessoaDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Endereco;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Pessoa;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.BairroRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.EnderecoRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.MunicipioRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.PessoaRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.DataIntegrityException;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private EnderecoRepository enderecoRepository;
    private MunicipioRepository municipioRepository;
    private UFService ufService;
    private BairroRepository bairroRepository;
    private BairroService bairroService;
    private MunicipioService municipioService;

    public PessoaService(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository,
                         MunicipioRepository municipioRepository, UFService ufService, BairroRepository bairroRepository, BairroService bairroService, MunicipioService municipioService) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
        this.municipioRepository = municipioRepository;
        this.ufService = ufService;
        this.bairroRepository = bairroRepository;
        this.bairroService = bairroService;
        this.municipioService = municipioService;
    }

    @Transactional(readOnly = true)
    public List<PessoaDto> buscarTodos() {

        List<Pessoa> lista = pessoaRepository.buscarTodasPessoa();
        return lista.stream().map(elemento -> new PessoaDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PessoaDto> buscarPorParametros(String codigoPessoa, String login, String status) {

        validarParametroInteger(codigoPessoa, status);
        Integer valorCodigoPessoa = null;
        Integer valorStatus = null;

        if (codigoPessoa != null) {
            valorCodigoPessoa = Integer.parseInt(codigoPessoa);
        }
        if (status != null) {
            valorStatus = Integer.parseInt(status);
        }

        List<Pessoa> lista = pessoaRepository.buscarPorParametro(valorCodigoPessoa, login, valorStatus);
        List<PessoaDto> dto = new ArrayList<>();
        for (Pessoa item : lista) {
            dto.add(buscarPessoaComEndereco(item));
        }
        return dto;
    }

    @Transactional
    public PessoaDto salvar(PessoaDto pessoaDto) {

        validarPeloLogin(pessoaDto);
        Pessoa entidade = new Pessoa();
        copiarDtoParaEntidadeSalvar(pessoaDto, entidade);
        entidade = pessoaRepository.save(entidade);
        return new PessoaDto(entidade);
    }

    @Transactional
    public PessoaDto atualizar(PessoaDto pessoaDto) {

        Pessoa entidade = pessoaRepository.buscarPeloCodigoPessoa(pessoaDto.getCodigoPessoa()).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Pessoa n??o encontrado"));
        validarPeloLogin(pessoaDto);
        copiarDtoParaEntidadeAtualizar(pessoaDto, entidade);
        entidade = pessoaRepository.save(entidade);
        return new PessoaDto(entidade);
    }

    @Transactional
    public void deletar(Integer codigoPessoa) {

        Pessoa entidade = pessoaRepository.buscarPeloCodigoPessoa(codigoPessoa).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Pessoa n??o encontrado"));
        entidade.setStatus(0);
        pessoaRepository.save(entidade);
    }

    private void validarPeloLogin(PessoaDto pessoaDto) {
        Optional<Pessoa> entidade = pessoaRepository.buscarPeloLogin(pessoaDto.getLogin());
        if (entidade.isPresent() && entidade.get().getCodigoPessoa() != pessoaDto.getCodigoPessoa()) {
            throw new DataIntegrityException("N??o foi poss??vel incluir PESSOA no banco de dados.<br>Motivo:" +
                    " J?? existe um(a) registro de PESSOA com o login " + pessoaDto.getLogin() +
                    " cadastrado no banco de dados.");
        }
    }

    private PessoaDto buscarPessoaComEndereco(Pessoa entidade) {
        PessoaDto dto = new PessoaDto(entidade);

        for (Endereco elementoLista : entidade.getEnderecos()) {

            EnderecoDto enderecoDto = new EnderecoDto(elementoLista);
            BairroDto bairroDto = bairroService.buscarPeloCodigoBairro(elementoLista.getCodigoBairro().getCodigoBairro());
            MunicipioDto municipioDto = municipioService.buscarPeloCodigoMunicipio(bairroDto.getCodigoMunicipio());
            UFDto ufDto = ufService.buscarPeloCodigoUF(municipioDto.getCodigoUF());

            BairroGetDto bairroGetDto = new BairroGetDto(
                    bairroDto.getCodigoBairro(),
                    bairroDto.getCodigoMunicipio(),
                    bairroDto.getNome(),
                    bairroDto.getStatus(),
                    municipioDto
            );

            MunicipioGetDto municipioGetDto = new MunicipioGetDto(
                    municipioDto.getCodigoMunicipio(),
                    municipioDto.getCodigoUF(),
                    municipioDto.getNome(),
                    municipioDto.getStatus(),
                    ufDto
            );

            bairroGetDto.setMunicipio(municipioGetDto);
            enderecoDto.setBairro(bairroGetDto);

            dto.getEnderecos().add(enderecoDto);
        }
        return dto;
    }

    private void copiarDtoParaEntidadeSalvar(PessoaDto dto, Pessoa entidade) {

        entidade.setNome(dto.getNome());
        entidade.setSobreNome(dto.getSobrenome());
        entidade.setIdade(dto.getIdade());
        entidade.setLogin(dto.getLogin());
        entidade.setSenha(dto.getSenha());
        entidade.setStatus(dto.getStatus());

        if (dto.getEnderecos().isEmpty()) {
            throw new DataIntegrityException("N??o foi poss??vel incluir PESSOA no banco de dados.<br>Motivo:" +
                    " N??o ?? poss??vel cadastrar pessoa sem endere??o.");
        }
        for (EnderecoDto enderecoDto : dto.getEnderecos()) {

            Endereco endereco = new Endereco();
            Bairro bairro = bairroRepository.buscarPeloCodigoBairro(enderecoDto.getCodigoBairro()).orElseThrow(
                    () -> new ResourceNotFoundException("Codigo Bairro n??o encontrado"));
            copiarEndereoDtoParaEntidade(endereco, entidade, bairro, enderecoDto);
            entidade.getEnderecos().add(endereco);
        }
    }

    private void copiarDtoParaEntidadeAtualizar(PessoaDto dto, Pessoa entidade) {

        entidade.setNome(dto.getNome());
        entidade.setSobreNome(dto.getSobrenome());
        entidade.setIdade(dto.getIdade());
        entidade.setLogin(dto.getLogin());
        entidade.setSenha(dto.getSenha());
        entidade.setStatus(dto.getStatus());

        if (dto.getEnderecos().isEmpty()) {
            throw new DataIntegrityException("N??o foi poss??vel incluir PESSOA no banco de dados.<br>Motivo:" +
                    " N??o ?? poss??vel cadastrar pessoa sem endere??o.");
        }

        List<Endereco> novoEndereco = new ArrayList<>();

        for (EnderecoDto enderecoDto : dto.getEnderecos()) {

            Bairro bairro = bairroRepository.buscarPeloCodigoBairro(enderecoDto.getCodigoBairro()).orElseThrow(
                    () -> new ResourceNotFoundException("Codigo Bairro n??o encontrado"));

            for (Endereco endereco : entidade.getEnderecos()) {
                if (enderecoDto.getCodigoEndereco() == endereco.getCodigoEndereco()) {

                    copiarEndereoDtoParaEntidade(endereco, entidade, bairro, enderecoDto);
                    Endereco enderecoNoBanco = enderecoRepository.buscarPeloCodigoEndereco(endereco.getCodigoEndereco());
                    novoEndereco.add(enderecoNoBanco);

                } else if (enderecoDto.getCodigoEndereco() == null) {

                    Endereco ende = new Endereco();
                    copiarEndereoDtoParaEntidade(ende, entidade, bairro, enderecoDto);
                    novoEndereco.add(ende);
                    break;
                }
            }
        }
        entidade.getEnderecos().clear();

        for (Endereco item : novoEndereco) {
            entidade.getEnderecos().add(item);
        }
    }

    private void copiarEndereoDtoParaEntidade(Endereco endereco, Pessoa entidade, Bairro bairro, EnderecoDto enderecoDto) {
        endereco.setCodigoPessoa(entidade);
        endereco.setCodigoBairro(bairro);
        endereco.setNomeRua(enderecoDto.getNomeRua());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setComplemento(enderecoDto.getComplemento());
        endereco.setCep(enderecoDto.getCep());
    }

    private void validarParametroInteger(String codigoPessoa, String status) {
        try {
            if (codigoPessoa != null) {
                Integer.parseInt(codigoPessoa);
            }
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("N??o foi poss??vel consultar PESSOA no banco de dados." +
                    "<br>Motivo: O valor do campo codigoPessoa precisa ser n??mero, e voc?? passo '" + codigoPessoa + "'.");
        }

        try {
            if (status != null) {
                Integer.parseInt(status);
            }
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("N??o foi poss??vel consultar PESSOA no banco de dados." +
                    "<br>Motivo: O valor do campo status precisa ser n??mero, e voc?? passo '" + status + "'.");
        }
    }
}
