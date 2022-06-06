package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.*;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PessoaGetAllDto>> buscarTodos() {
        List<PessoaGetAllDto> lista = pessoaService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(params = "nome")
    public ResponseEntity<List<PessoaGetAllDto>> buscarTodosPeloNome(@RequestParam String nome) {
        List<PessoaGetAllDto> lista = pessoaService.buscarTodosPeloNome(nome);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(params = "codigoPessoa")
    public ResponseEntity<PessoaDto> buscarPeloCodigoPessoa(@RequestParam Integer codigoPessoa) {
        PessoaDto pessoaDto = pessoaService.buscarPeloCodigoPessoa(codigoPessoa);
        return ResponseEntity.ok().body(pessoaDto);
    }

    @GetMapping(params = "login")
    public ResponseEntity<PessoaDto> buscarPeloLogin(@RequestParam String login) {
        PessoaDto pessoaDto = pessoaService.buscarPeloLogin(login);
        return ResponseEntity.ok().body(pessoaDto);
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<PessoaGetAllDto>> buscarPeloStatus(@RequestParam Integer status) {
        List<PessoaGetAllDto> pessoaDto = pessoaService.buscarPeloStatus(status);
        return ResponseEntity.ok().body(pessoaDto);
    }

    @PostMapping
    public ResponseEntity<List<PessoaGetAllDto>> salvar(@Valid @RequestBody PessoaInsertDto pessoaDto) {
        pessoaService.salvar(pessoaDto);
        List<PessoaGetAllDto> lista = pessoaService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping
    public ResponseEntity<List<PessoaGetAllDto>> atualizar(@Valid @RequestBody PessoaUpdateDto pessoaDto) {
        pessoaService.atualizar(pessoaDto);
        List<PessoaGetAllDto> lista = pessoaService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{codigoPessoa}")
    public ResponseEntity<List<PessoaGetAllDto>> deletar(@PathVariable Integer codigoPessoa) {
        pessoaService.deletar(codigoPessoa);
        List<PessoaGetAllDto> lista = pessoaService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }
}
