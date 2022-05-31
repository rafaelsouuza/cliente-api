package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.UF;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.UFService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/uf")
public class UFResource {

    private UFService ufService;

    public UFResource(UFService ufService) {
        this.ufService = ufService;
    }

    @GetMapping
    public ResponseEntity<List<UF>> buscarTodosUf() {

        List<UF> lista = ufService.buscarTodosUf();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(params = "codigoUF")
    public ResponseEntity<UF> buscarPeloCodigoUF(@RequestParam Integer codigoUF) {
        UF uf = ufService.buscarPeloCodigoUF(codigoUF);
        return ResponseEntity.ok().body(uf);
    }

    @GetMapping(params = "sigla")
    public ResponseEntity<UF> buscarPelaSigla(@RequestParam String sigla) {
        UF uf = ufService.buscarPelaSigla(sigla);
        return ResponseEntity.ok().body(uf);
    }
}
