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

    @Query("SELECT c FROM Pessoa c WHERE c.nome = :nome")
    List<Pessoa> buscarPeloNome(@Param("nome") String nome);

    @Query("SELECT c FROM Pessoa c WHERE c.login = :login")
    Optional<Pessoa> buscarPeloLogin(@Param("login") String login);

    @Query("SELECT c FROM Pessoa c WHERE c.status = :status")
    List<Pessoa> bucarPeloStatus(@Param("status") Integer status);

}
