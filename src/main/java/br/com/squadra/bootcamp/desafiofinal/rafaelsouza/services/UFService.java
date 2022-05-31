package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.UFRespository;
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
        List<UF> lista = ufRespository.findAll();
        return lista.stream().map(elemento -> new UFDto(elemento)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UFDto buscarPeloCodigoUF(Integer codigoUF) {
        UF ufCodigo = ufRespository.bucarPeloCodigoUF(codigoUF).get();
        return new UFDto(ufCodigo);
    }

    @Transactional(readOnly = true)
    public UFDto buscarPelaSigla(String sigla) {
        UF ufSigla = ufRespository.bucarPelaSigla(sigla).get();
        return new UFDto(ufSigla);
    }

    @Transactional
    public UFDto salvarUF(UFDto ufDto) {
        UF uf = new UF();
        copiarDtoParaEntidade(ufDto, uf);
        uf = ufRespository.save(uf);
        return new UFDto(uf);
    }

    private void copiarDtoParaEntidade(UFDto dto, UF entidade) {
        entidade.setNome(dto.getNome());
        entidade.setSigla(dto.getSigla());
        entidade.setStatus(dto.getStatus());
    }
}
