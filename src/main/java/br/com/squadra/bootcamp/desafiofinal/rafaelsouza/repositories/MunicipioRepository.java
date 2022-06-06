package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    @Query("SELECT c FROM Municipio c WHERE c.codigoMunicipio = :codigoMunicipio")
    Optional<Municipio> buscarPeloCodigoMunicipio(@Param("codigoMunicipio") Integer codigoMunicipio);

    @Query("SELECT c FROM Municipio c")
    List<Municipio> buscarTodosMunicipio();

    @Query("SELECT c FROM Municipio c WHERE c.codigoUF.codigoUF = :codigoUF")
    List<Municipio> buscarTodosPeloCodigoUF(@Param("codigoUF") Integer codigoUF);

    @Query("SELECT c FROM Municipio c WHERE c.nome = :nome")
    Optional<Municipio> bucarPeloNome(@Param("nome") String nome);

    @Query("SELECT c FROM Municipio c WHERE c.status = :status")
    List<Municipio> bucarPeloStatus(@Param("status") Integer status);

}
