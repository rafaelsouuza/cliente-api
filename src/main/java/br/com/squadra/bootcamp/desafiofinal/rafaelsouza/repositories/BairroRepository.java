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
    List<Bairro> buscarPeloCodigoMunicipio(@Param("codigoMunicipio") Integer codigoMunicipio);

    @Query("SELECT c FROM Bairro c WHERE 1 = 1 AND "
            +   "(:codigoBairro IS NULL OR c.codigoBairro = :codigoBairro) AND "
            +   "(:codigoMunicipio IS NULL OR c.codigoMunicipio.codigoMunicipio = :codigoMunicipio) AND "
            +   "(:nome IS NULL OR c.nome = :nome) AND "
            +   "(:status IS NULL OR c.status = :status)"
    )
    List<Bairro> buscarPorParametro(
            @Param("codigoBairro") Integer codigoBairro,
            @Param("codigoMunicipio") Integer codigoMunicipio,
            @Param("nome") String nome,
            @Param("status") Integer status
    );

}
