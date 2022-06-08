package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.MunicipioDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Municipio;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.MunicipioRepository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.DataIntegrityException;
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
    public List<MunicipioDto> buscarPorParametros(String codigoMunicipio, String codigoUf, String nome, String status) {

        validarParametroInteger(codigoMunicipio, codigoUf, status);

        Integer valorMunicipio = null;
        Integer valorCodigoUf = null;
        Integer valorStatus = null;

        if (codigoMunicipio != null) {
            valorMunicipio = Integer.parseInt(codigoMunicipio);
        }
        if (codigoUf != null) {
            valorCodigoUf = Integer.parseInt(codigoUf);
        }
        if (status != null) {
            valorStatus = Integer.parseInt(status);
        }

        List<Municipio> lista = municipioRepository.buscarPorParametro(valorMunicipio, valorCodigoUf, nome, valorStatus);
        return lista.stream().map(elemento -> new MunicipioDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MunicipioDto buscarPeloCodigoMunicipio(Integer codigoMunicipio) {
        Municipio entidade = municipioRepository.buscarPeloCodigoMunicipio(codigoMunicipio).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Município não encontrado"));
        return new MunicipioDto(entidade);
    }

    @Transactional
    public MunicipioDto salvar(MunicipioDto municipioDto) {
        validarMunicipioUF(municipioDto);
        Municipio entidade = new Municipio();
        copiarDtoParaEntidade(municipioDto, entidade);
        entidade = municipioRepository.save(entidade);
        return new MunicipioDto(entidade);
    }

    @Transactional
    public MunicipioDto atualizar(MunicipioDto municipioDto) {
        Municipio entidade = municipioRepository.buscarPeloCodigoMunicipio(municipioDto.getCodigoMunicipio()).orElseThrow(
                () -> new ResourceNotFoundException("Codigo Município não encontrado"));
        validarMunicipioUF(municipioDto);
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

    // Valida um único Municipio com o mesmo Nome por UF
    private void validarMunicipioUF(MunicipioDto dto) {

        List<Municipio> lista = municipioRepository.buscarPeloCodigoUf(dto.getCodigoUF());
        for (Municipio item : lista) {
            if (item.getNome().equalsIgnoreCase(dto.getNome()) && item.getCodigoMunicipio() != dto.getCodigoMunicipio()) {
                throw new DataIntegrityException("Não foi possível incluir MUNICÍPIO no banco de dados.<br>Motivo:" +
                        " Já existe um(a) registro de MUNICÍPIO com o nome " + dto.getNome().toUpperCase() + " cadastrado no banco de dados.");
            }
        }
    }

    private void validarParametroInteger(String codigoMunicipio, String codigoUf, String status) {
        try {
            if (codigoMunicipio != null) {
                Integer.parseInt(codigoMunicipio);
            }
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("Não foi possível consultar MUNICÍPIO no banco de dados." +
                    "<br>Motivo: O valor do campo codigoMunicipio precisa ser número, e você passo '" + codigoMunicipio + "'.");
        }

        try {
            if (codigoUf != null) {
                Integer.parseInt(codigoUf);
            }
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("Não foi possível consultar MUNICÍPIO no banco de dados." +
                    "<br>Motivo: O valor do campo codigoUf precisa ser número, e você passo '" + codigoUf + "'.");
        }

        try {
            if (status != null) {
                Integer.parseInt(status);
            }
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("Não foi possível consultar MUNICÍPIO no banco de dados." +
                    "<br>Motivo: O valor do campo status precisa ser número, e você passo '" + status + "'.");
        }
    }
}
