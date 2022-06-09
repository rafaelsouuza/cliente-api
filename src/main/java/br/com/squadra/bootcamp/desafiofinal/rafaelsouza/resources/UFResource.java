package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.MunicipioDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.UFDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.UFService;
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
    public ResponseEntity<?> buscarPorParametros(
            @RequestParam(value = "codigoUF", required = false) String codigoUf,
            @RequestParam(value = "sigla", required = false) String sigla,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) String status
    ) {
        List<UFDto> buscaPersonalizada = ufService.buscarPorParametros(codigoUf, sigla, nome, status);
        if (codigoUf != null || sigla != null || nome != null) {
            for (UFDto item : buscaPersonalizada) {
                return ResponseEntity.ok().body(item);
            }
        }
        return ResponseEntity.ok().body(buscaPersonalizada);
    }

    @PostMapping
    public ResponseEntity<List<UFDto>> salvar(@Valid @RequestBody UFDto ufDto) {
        ufService.salvar(ufDto);
        List<UFDto> lista = ufService.buscarTodosUf();
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping
    public ResponseEntity<List<UFDto>> atualizar(@Valid @RequestBody UFDto ufDto) {
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
