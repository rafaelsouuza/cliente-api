package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.*;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Endereco;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Municipio;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Pessoa;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.BairroRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.EnderecoRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.MunicipioRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.PessoaRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<PessoaGetAllDto> buscarTodos() {
        List<Pessoa> lista = pessoaRepository.buscarTodasPessoa();
        return lista.stream().map(elemento -> new PessoaGetAllDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PessoaDto buscarPeloCodigoPessoa(Integer codigoPessoa) {

        Pessoa entidade = pessoaRepository.buscarPeloCodigoPessoa(codigoPessoa).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Pessoa não encontrado"));
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

    @Transactional
    public PessoaDto salvar(PessoaDto pessoaDto) {
        Pessoa entidade = new Pessoa();
        copiarDtoParaEntidade(pessoaDto, entidade);
        entidade = pessoaRepository.save(entidade);
        enderecoRepository.saveAll(entidade.getEnderecos());
        return new PessoaDto(entidade);
    }

    @Transactional
    public PessoaDto atualizar(PessoaDto pessoaDto) {
        Pessoa entidade = pessoaRepository.buscarPeloCodigoPessoa(pessoaDto.getCodigoPessoa()).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Município não encontrado"));
        //copiarDtoParaEntidade(pessoaDto, entidade);
        entidade = pessoaRepository.save(entidade);
        return new PessoaDto(entidade);
    }

    @Transactional
    public void deletar(Integer codigoMunicipio) {
        Municipio entidade = municipioRepository.buscarPeloCodigoMunicipio(codigoMunicipio).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Município não encontrado"));
        entidade.setStatus(0);
        municipioRepository.save(entidade);
    }

    private void copiarDtoParaEntidade(PessoaDto dto, Pessoa entidade) {

        entidade.setNome(dto.getNome());
        entidade.setSobreNome(dto.getSobrenome());
        entidade.setIdade(dto.getIdade());
        entidade.setLogin(dto.getLogin());
        entidade.setSenha(dto.getSenha());
        entidade.setStatus(dto.getStatus());

        for (EnderecoDto enderecoDto : dto.getEnderecos()) {
            Endereco endereco = new Endereco();
            Bairro bairro = bairroRepository.buscarPeloCodigoBairro(enderecoDto.getCodigoBairro()).orElseThrow(
                    () -> new ResourceNotFoundException("Codigo Bairro não encontrado"));
            endereco.setCodigoPessoa(entidade);
            endereco.setCodigoBairro(bairro);
            endereco.setNomeRua(enderecoDto.getNomeRua());
            endereco.setNumero(enderecoDto.getNumero());
            endereco.setComplemento(enderecoDto.getComplemento());
            endereco.setCep(enderecoDto.getCep());
            entidade.getEnderecos().add(endereco);
        }
    }
}
