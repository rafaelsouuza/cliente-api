package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFInsertDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFUpdateDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.UFService;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/uf")
public class UFResource {

    private UFService ufService;

    public UFResource(UFService ufService) {
        this.ufService = ufService;
    }

    @GetMapping
    public ResponseEntity<List<UFDto>> buscarTodos() {
        List<UFDto> lista = ufService.buscarTodosUf();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(params = "codigoUF")
    public ResponseEntity<UFDto> buscarPeloCodigoUF(@RequestParam Integer codigoUF) {
        UFDto uf = ufService.buscarPeloCodigoUF(codigoUF);
        return ResponseEntity.ok().body(uf);
    }

    @GetMapping(params = "sigla")
    public ResponseEntity<UFDto> buscarPelaSigla(@RequestParam String sigla) {
        UFDto uf = ufService.buscarPelaSigla(sigla.toUpperCase());
        return ResponseEntity.ok().body(uf);
    }

    @PostMapping
    public ResponseEntity<List<UFDto>> salvar(@Valid @RequestBody UFInsertDto ufDto) {
        ufService.salvar(ufDto);
        List<UFDto> lista = ufService.buscarTodosUf();
        return ResponseEntity.status(HttpStatus.CREATED).body(lista);
    }

    @PutMapping
    public ResponseEntity<List<UFDto>> atualizar(@Valid @RequestBody UFUpdateDto ufDto) {
        ufService.atualizar(ufDto);
        List<UFDto> lista = ufService.buscarTodosUf();
        return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{codigoUF}")
    public ResponseEntity<List<UFDto>> deletar(@PathVariable Integer codigoUF) {
        ufService.deletar(codigoUF);
        List<UFDto> lista = ufService.buscarTodosUf();
        return ResponseEntity.ok().body(lista);
    }
}
