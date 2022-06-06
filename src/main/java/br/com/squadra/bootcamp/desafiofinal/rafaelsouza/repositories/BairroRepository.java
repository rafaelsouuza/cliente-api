package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Integer> {

    @Query("SELECT c FROM Bairro c WHERE c.codigoBairro = :codigoBairro")
    Optional<Bairro> buscarPeloCodigoBairro(@Param("codigoBairro") Integer codigoBairro);

    @Query("SELECT c FROM Bairro c")
    List<Bairro> buscarTodosBairro();

    @Query("SELECT c FROM Bairro c WHERE c.codigoMunicipio.codigoMunicipio = :codigoMunicipio")
    List<Bairro> buscarTodosPeloCodigoMunicipio(@Param("codigoMunicipio") Integer codigoMunicipio);

    @Query("SELECT c FROM Bairro c WHERE c.nome = :nome")
    Optional<Bairro> bucarPeloNome(@Param("nome") String nome);

    @Query("SELECT c FROM Bairro c WHERE c.status = :status")
    List<Bairro> bucarPeloStatus(@Param("status") Integer status);

}
