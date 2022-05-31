package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories.UFRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UFService {

    private UFRespository ufRespository;

    public UFService(UFRespository ufRespository) {
        this.ufRespository = ufRespository;
    }

    @Transactional(readOnly = true)
    public List<UF> buscarTodosUf() {
        List<UF> lista = ufRespository.findAll();
        return lista;
    }

    @Transactional(readOnly = true)
    public UF buscarPeloCodigoUF(Integer codigoUF) {
        return ufRespository.bucarPeloCodigoUF(codigoUF).get();
    }

    @Transactional(readOnly = true)
    public UF buscarPelaSigla(String sigla) {
        return ufRespository.bucarPelaSigla(sigla).get();
    }
}
