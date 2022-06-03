package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Bairro, Integer> {

    @Query("SELECT c FROM Pessoa c WHERE c.codigoPessoa = :codigoPessoa AND c.status = 1")
    Optional<Pessoa> buscarPeloCodigoPessoa(@Param("codigoPessoa") Integer codigoPessoa);

    @Query("SELECT c FROM Pessoa c WHERE c.status = 1")
    List<Pessoa> buscarTodasPessoa();

}
