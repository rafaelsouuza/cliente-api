package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Integer> {

    @Query("SELECT c FROM Bairro c WHERE c.codigoBairro = :codigoBairro AND c.status = 1")
    Optional<Bairro> buscarPeloCodigoBairro(@Param("codigoBairro") Integer codigoBairro);

    @Query("SELECT c FROM Bairro c WHERE c.status = 1")
    List<Bairro> buscarTodosBairro();

    @Query("SELECT c FROM Bairro c WHERE c.codigoMunicipio.codigoMunicipio = :codigoMunicipio AND c.status = 1")
    List<Bairro> buscarTodosPeloCodigoMunicipio(@Param("codigoMunicipio") Integer codigoMunicipio);

}
