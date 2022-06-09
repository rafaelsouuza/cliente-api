package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.PessoaDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

    private PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<?> buscarPorParametros(
            @RequestParam(value = "codigoPessoa", required = false) String codigoPessoa,
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "status", required = false) String status
    ) {
        List<PessoaDto> buscaPersonalizada = pessoaService.buscarPorParametros(codigoPessoa, login, status);
        if (codigoPessoa != null) {
            PessoaDto buscarPeLoCodigo = pessoaService.buscarPeloCodigoPessoa(codigoPessoa);
            return ResponseEntity.ok(buscarPeLoCodigo);
        }
        return ResponseEntity.ok().body(buscaPersonalizada);
    }

    @PostMapping
    public ResponseEntity<List<PessoaDto>> salvar(@Valid @RequestBody PessoaDto pessoaDto) {
        pessoaService.salvar(pessoaDto);
        List<PessoaDto> lista = pessoaService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping
    public ResponseEntity<List<PessoaDto>> atualizar(@Valid @RequestBody PessoaDto pessoaDto) {
        pessoaService.atualizar(pessoaDto);
        List<PessoaDto> lista = pessoaService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{codigoPessoa}")
    public ResponseEntity<List<PessoaDto>> deletar(@PathVariable Integer codigoPessoa) {
        pessoaService.deletar(codigoPessoa);
        List<PessoaDto> lista = pessoaService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }
}
