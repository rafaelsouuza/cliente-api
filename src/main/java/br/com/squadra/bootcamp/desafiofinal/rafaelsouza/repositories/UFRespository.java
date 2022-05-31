package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UFRespository extends JpaRepository<UF, Integer> {

    @Query("SELECT c FROM UF c WHERE c.codigoUF = :codigoUF")
    Optional<UF> bucarPeloCodigoUF(@Param("codigoUF") Integer codigoUF);

    @Query("SELECT c FROM UF c WHERE c.sigla = :sigla")
    Optional<UF> bucarPelaSigla(@Param("sigla") String sigla);
}