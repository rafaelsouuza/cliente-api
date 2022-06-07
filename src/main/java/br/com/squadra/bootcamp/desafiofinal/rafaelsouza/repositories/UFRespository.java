package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UFRespository extends JpaRepository<UF, Integer> {

    @Query("SELECT c FROM UF c")
    List<UF> buscarTodosUF();

    @Query("SELECT c FROM UF c WHERE c.codigoUF = :codigoUF")
    Optional<UF> bucarPeloCodigoUF(@Param("codigoUF") Integer codigoUF);

    @Query("SELECT c FROM UF c WHERE c.sigla = :sigla")
    Optional<UF> bucarPelaSigla(@Param("sigla") String sigla);

    @Query("SELECT c FROM UF c WHERE c.nome = :nome")
    Optional<UF> bucarPeloNome(@Param("nome") String nome);

    @Query("SELECT c FROM UF c WHERE c.status = :status")
    List<UF> bucarPeloStatus(@Param("status") Integer status);

    @Query("SELECT c FROM UF c WHERE 1 = 1 AND "
        +   "(:codigoUF IS NULL OR c.codigoUF = :codigoUF) AND "
            +   "(:sigla IS NULL OR c.sigla = :sigla) AND "
            +   "(:nome IS NULL OR c.nome = :nome) AND "
            +   "(:status IS NULL OR c.status = :status)"
    )
    List<UF> buscarPorParametro(
            @Param("codigoUF") Integer codigoUF,
            @Param("sigla") String sigla,
            @Param("nome") String nome,
            @Param("status") Integer status
    );
}
