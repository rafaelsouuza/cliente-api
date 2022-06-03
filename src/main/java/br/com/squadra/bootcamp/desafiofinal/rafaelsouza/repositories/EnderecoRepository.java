package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.repositories;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Query("SELECT c FROM Endereco c WHERE c.codigoEndereco = :codigoEndereco")
    Endereco buscarPeloCodigoEndereco(@Param("codigoEndereco") Integer codigoEndereco);
}
