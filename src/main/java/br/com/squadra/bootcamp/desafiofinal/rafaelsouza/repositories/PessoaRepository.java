package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query("SELECT c FROM Pessoa c WHERE c.codigoPessoa = :codigoPessoa")
    Optional<Pessoa> buscarPeloCodigoPessoa(@Param("codigoPessoa") Integer codigoPessoa);

    @Query("SELECT c FROM Pessoa c")
    List<Pessoa> buscarTodasPessoa();

    @Query("SELECT c FROM Pessoa c WHERE c.login = :login")
    Optional<Pessoa> buscarPeloLogin(@Param("login") String login);

    @Query("SELECT c FROM Pessoa c WHERE 1 = 1 AND "
            +   "(:codigoPessoa IS NULL OR c.codigoPessoa = :codigoPessoa) AND "
            +   "(:login IS NULL OR c.login = :login) AND "
            +   "(:status IS NULL OR c.status = :status)"
    )
    List<Pessoa> buscarPorParametro(
            @Param("codigoPessoa") Integer codigoPessoa,
            @Param("login") String login,
            @Param("status") Integer status
    );

}
