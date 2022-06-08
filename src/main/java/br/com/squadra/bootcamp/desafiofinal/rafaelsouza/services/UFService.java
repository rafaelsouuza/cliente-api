package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.UFRespository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.DataIntegrityException;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UFService {

    private UFRespository ufRespository;

    public UFService(UFRespository ufRespository) {
        this.ufRespository = ufRespository;
    }

    @Transactional(readOnly = true)
    public List<UFDto> buscarTodosUf() {
        List<UF> lista = ufRespository.buscarTodosUF();
        return lista.stream().map(elemento -> new UFDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UFDto> buscarPorParametros(Integer codigoUf, String sigla, String nome, Integer status) {

        List<UF> lista = ufRespository.buscarPorParametro(codigoUf, sigla, nome, status);
        return lista.stream().map(elemento -> new UFDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UFDto buscarPeloCodigoUF(Integer codigoUF) {
        UF entidade = ufRespository.bucarPeloCodigoUF(codigoUF).orElseThrow(
                () -> new ResourceNotFoundException("Codigo UF não encontrado"));
        return new UFDto(entidade);
    }

    @Transactional
    public UFDto salvar(UFDto ufDto) {
        validarPorSiglaENome(ufDto);
        UF entidade = new UF();
        copiarDtoParaEntidade(ufDto, entidade);
        entidade = ufRespository.save(entidade);
        return new UFDto(entidade);
    }

    @Transactional
    public UFDto atualizar(UFDto ufDto) {
        UF entidade = ufRespository.bucarPeloCodigoUF(ufDto.getCodigoUF()).orElseThrow(
                () -> new ResourceNotFoundException("Codigo UF não encontrado"));
        validarPorSiglaENome(ufDto);
        copiarDtoParaEntidade(ufDto, entidade);
        entidade = ufRespository.save(entidade);
        return new UFDto(entidade);
    }

    @Transactional
    public void deletar(Integer codigoUF) {
        UF entidade = ufRespository.bucarPeloCodigoUF(codigoUF).orElseThrow(
                () -> new ResourceNotFoundException("Codigo UF não encontrado"));
        entidade.setStatus(2);
        ufRespository.save(entidade);
    }

    private void copiarDtoParaEntidade(UFDto dto, UF entidade) {
        entidade.setNome(dto.getNome().toUpperCase());
        entidade.setSigla(dto.getSigla().toUpperCase());
        entidade.setStatus(dto.getStatus());
    }

    private void validarPorSiglaENome(UFDto dto) {

        Optional<UF> entidade = ufRespository.bucarPelaSigla(dto.getSigla().toUpperCase());
        if (entidade.isPresent() && entidade.get().getCodigoUF() != dto.getCodigoUF()) {
            throw new DataIntegrityException("Não foi possível incluir UF no banco de dados.<br>Motivo:" +
                    " Já existe um(a) registro de UF com a sigla " + dto.getSigla().toUpperCase() + " cadastrado no banco de dados.");
        }
        entidade = ufRespository.bucarPeloNome(dto.getNome().toUpperCase());
        if (entidade.isPresent() && entidade.get().getCodigoUF() != dto.getCodigoUF()) {
            throw new DataIntegrityException("Não foi possível incluir UF no banco de dados.<br>Motivo:" +
                    " Já existe um(a) registro de UF com o nome " + dto.getNome().toUpperCase() + " cadastrado no banco de dados.");
        }
    }
}
