package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.MunicipioDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Municipio;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.MunicipioRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    private MunicipioRepository municipioRepository;
    private UFService ufService;

    public MunicipioService(MunicipioRepository municipioRepository, UFService ufService) {
        this.municipioRepository = municipioRepository;
        this.ufService = ufService;
    }

    @Transactional(readOnly = true)
    public List<MunicipioDto> buscarTodos() {
        List<Municipio> lista = municipioRepository.buscarTodosMunicipio();
        return lista.stream().map(elemento -> new MunicipioDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MunicipioDto> buscarTodosPeloCodigoUF(Integer codigoUF) {
        List<Municipio> lista = municipioRepository.buscarTodosPeloCodigoUF(codigoUF);
        return lista.stream().map(elemento -> new MunicipioDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MunicipioDto buscarPeloCodigoMunicipio(Integer codigoMunicipio) {
        Municipio entidade = municipioRepository.buscarPeloCodigoMunicipio(codigoMunicipio).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Município não encontrado"));
        return new MunicipioDto(entidade);
    }

    @Transactional(readOnly = true)
    public MunicipioDto buscarPelaNome(String nome) {
        Municipio entidade = municipioRepository.bucarPeloNome(nome).orElseThrow(
                () -> new ResourceNotFoundException("Nome de Município não encontrado"));
        return new MunicipioDto(entidade);
    }

    @Transactional(readOnly = true)
    public List<MunicipioDto> buscarPeloStatus(Integer status) {
        List<Municipio> lista = municipioRepository.bucarPeloStatus(status);
        return lista.stream().map(elemento -> new MunicipioDto(elemento)).collect(Collectors.toList());
    }

    @Transactional
    public MunicipioDto salvar(MunicipioDto municipioDto) {
        Municipio entidade = new Municipio();
        copiarDtoParaEntidade(municipioDto, entidade);
        entidade = municipioRepository.save(entidade);
        return new MunicipioDto(entidade);
    }

    @Transactional
    public MunicipioDto atualizar(MunicipioDto municipioDto) {
        Municipio entidade = municipioRepository.buscarPeloCodigoMunicipio(municipioDto.getCodigoMunicipio()).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Município não encontrado"));
        copiarDtoParaEntidade(municipioDto, entidade);
        entidade = municipioRepository.save(entidade);
        return new MunicipioDto(entidade);
    }

    @Transactional
    public void deletar(Integer codigoMunicipio) {
        Municipio entidade = municipioRepository.buscarPeloCodigoMunicipio(codigoMunicipio).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Município não encontrado"));
        entidade.setStatus(2);
        municipioRepository.save(entidade);
    }

    private void copiarDtoParaEntidade(MunicipioDto dto, Municipio entidade) {

        // Verifica se existe uma UF com codigoUF recebido
        UFDto ufEntidade = ufService.buscarPeloCodigoUF(dto.getCodigoUF());
        // Cria um objeto UF com o resultado encontrado no banco
        UF ufEntitidade = new UF(ufEntidade.getCodigoUF(), ufEntidade.getNome(),
                ufEntidade.getSigla(), ufEntidade.getStatus());

        entidade.setCodigoUF(ufEntitidade);
        entidade.setNome(dto.getNome().toUpperCase());
        entidade.setStatus(dto.getStatus());
    }
}
