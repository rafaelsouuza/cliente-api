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
    List<Municipio> buscarPeloCodigoUf(@Param("codigoUF") Integer codigoUF);

    @Query("SELECT c FROM Municipio c WHERE 1 = 1 AND "
            +   "(:codigoMunicipio IS NULL OR c.codigoMunicipio = :codigoMunicipio) AND "
            +   "(:codigoUF IS NULL OR c.codigoUF.codigoUF = :codigoUF) AND "
            +   "(:nome IS NULL OR c.nome = :nome) AND "
            +   "(:status IS NULL OR c.status = :status)"
    )
    List<Municipio> buscarPorParametro(
            @Param("codigoMunicipio") Integer codigoMunicipio,
            @Param("codigoUF") Integer codigoUF,
            @Param("nome") String nome,
            @Param("status") Integer status
    );
}
