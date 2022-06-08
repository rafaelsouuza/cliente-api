package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.BairroDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.MunicipioDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Municipio;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.BairroRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.DataIntegrityException;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BairroService {

    private BairroRepository bairroRepository;
    private MunicipioService municipioService;
    private UFService ufService;

    public BairroService(BairroRepository bairroRepository, MunicipioService municipioService, UFService ufService) {
        this.bairroRepository = bairroRepository;
        this.municipioService = municipioService;
        this.ufService = ufService;
    }

    @Transactional(readOnly = true)
    public List<BairroDto> buscarTodos() {
        List<Bairro> lista = bairroRepository.buscarTodosBairro();
        return lista.stream().map(elemento -> new BairroDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BairroDto> buscarPorParametros(Integer codigoBairro, Integer codigoMunicipio, String nome, Integer status) {

        List<Bairro> lista = bairroRepository.buscarPorParametro(codigoBairro, codigoMunicipio, nome, status);
        return lista.stream().map(elemento -> new BairroDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BairroDto buscarPeloCodigoBairro(Integer codigoBairro) {
        Bairro entidade = bairroRepository.buscarPeloCodigoBairro(codigoBairro).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Bairro não encontrado"));
        return new BairroDto(entidade);
    }

    @Transactional
    public BairroDto salvar(BairroDto bairroDto) {
        validarBairroMunicipio(bairroDto);
        Bairro entidade = new Bairro();
        copiarDtoParaEntidade(bairroDto, entidade);
        entidade = bairroRepository.save(entidade);
        return new BairroDto(entidade);
    }

    @Transactional
    public BairroDto atualizar(BairroDto bairroDto) {
        Bairro entidade = bairroRepository.buscarPeloCodigoBairro(bairroDto.getCodigoBairro()).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Bairro não encontrado"));
        validarBairroMunicipio(bairroDto);
        copiarDtoParaEntidade(bairroDto, entidade);
        entidade = bairroRepository.save(entidade);
        return new BairroDto(entidade);
    }

    @Transactional
    public void deletar(Integer codigoBairro) {
        Bairro entidade = bairroRepository.buscarPeloCodigoBairro(codigoBairro).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Bairro não encontrado"));
        entidade.setStatus(0);
        bairroRepository.save(entidade);
    }

    private void copiarDtoParaEntidade(BairroDto dto, Bairro entidade) {

        // Verifica se existe uma UF/Municipio com codigo recebido do objeto BairroDto
        MunicipioDto municipioDto = municipioService.buscarPeloCodigoMunicipio(dto.getCodigoMunicipio());
        UFDto ufDto = ufService.buscarPeloCodigoUF(municipioDto.getCodigoUF());

        // Instancia os objetos UF/Municipio
        UF ufEntitidade = new UF(ufDto.getCodigoUF(), ufDto.getNome(),
                ufDto.getSigla(), ufDto.getStatus());
        Municipio municipioEntidade = new Municipio(municipioDto.getCodigoMunicipio(),
                ufEntitidade, municipioDto.getNome(), municipioDto.getStatus());

        entidade.setCodigoMunicipio(municipioEntidade);
        entidade.setNome(dto.getNome().toUpperCase());
        entidade.setStatus(dto.getStatus());
    }

    // Valida um único Bairro com o mesmo Nome no Município
    private void validarBairroMunicipio(BairroDto dto) {

        List<Bairro> lista = bairroRepository.buscarPeloCodigoMunicipio(dto.getCodigoMunicipio());
        for (Bairro item : lista) {
            if (item.getNome().equalsIgnoreCase(dto.getNome()) && item.getCodigoBairro() != dto.getCodigoBairro()) {
                throw new DataIntegrityException("Não foi possível incluir BAIRRO no banco de dados.<br>Motivo:" +
                        " Já existe um(a) registro de bairro com o nome " + dto.getNome().toUpperCase() +
                        " para o mesmo município cadastrado no banco de dados.");
            }
        }
    }
}
