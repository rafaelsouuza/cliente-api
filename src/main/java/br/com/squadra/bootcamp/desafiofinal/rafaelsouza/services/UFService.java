package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFInsertDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFUpdateDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.UFRespository;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public UFDto buscarPeloCodigoUF(Integer codigoUF) {
        UF entidade = ufRespository.bucarPeloCodigoUF(codigoUF).orElseThrow(
                () -> new ResourceNotFoundException("Codigo UF não encontrado"));
        return new UFDto(entidade);
    }

    @Transactional(readOnly = true)
    public UFDto buscarPelaSigla(String sigla) {
        UF entidade = ufRespository.bucarPelaSigla(sigla).orElseThrow(
                () -> new ResourceNotFoundException("Sigla UF não encontrado"));
        return new UFDto(entidade);
    }

    @Transactional(readOnly = true)
    public UFDto buscarPelaNome(String nome) {
        UF entidade = ufRespository.bucarPeloNome(nome).orElseThrow(
                () -> new ResourceNotFoundException("Nome de UF não encontrado"));
        return new UFDto(entidade);
    }

    @Transactional(readOnly = true)
    public List<UFDto> buscarPeloStatus(Integer status) {
        List<UF> lista = ufRespository.bucarPeloStatus(status);
        return lista.stream().map(elemento -> new UFDto(elemento)).collect(Collectors.toList());
    }

    @Transactional
    public UFDto salvar(UFInsertDto ufDto) {
        UF entidade = new UF();
        copiarDtoParaEntidade(ufDto, entidade);
        entidade = ufRespository.save(entidade);
        return new UFDto(entidade);
    }

    @Transactional
    public UFDto atualizar(UFUpdateDto ufDto) {
        UF entidade = ufRespository.bucarPeloCodigoUF(ufDto.getCodigoUF()).orElseThrow(
                () -> new ResourceNotFoundException("Codigo UF não encontrado"));
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
}
