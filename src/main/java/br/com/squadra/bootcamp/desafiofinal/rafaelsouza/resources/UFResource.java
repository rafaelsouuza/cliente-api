package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.UFService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/uf")
public class UFResource {

    private UFService ufService;

    public UFResource(UFService ufService) {
        this.ufService = ufService;
    }

    @GetMapping
    public ResponseEntity<List<UFDto>> buscarTodosUf() {
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
        UFDto uf = ufService.buscarPelaSigla(sigla);
        return ResponseEntity.ok().body(uf);
    }

    @PostMapping
    public ResponseEntity<List<UFDto>> salvarUF(@RequestBody UFDto ufDto) {
        ufService.salvarUF(ufDto);
        List<UFDto> lista = ufService.buscarTodosUf();
        return ResponseEntity.status(HttpStatus.CREATED).body(lista);
    }
}
