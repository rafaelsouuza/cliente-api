package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.BairroDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.BairroService;
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
@RequestMapping("/bairro")
public class BairroResource {

    private BairroService bairroService;

    public BairroResource(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    @GetMapping
    public ResponseEntity<?> buscarPorParametros(
            @RequestParam(value = "codigoBairro", required = false) String codigoBairro,
            @RequestParam(value = "codigoMunicipio", required = false) String codigoMunicipio,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) String status
    ) {
        List<BairroDto> buscaPersonalizada = bairroService.buscarPorParametros(codigoBairro, codigoMunicipio, nome, status);
        if (codigoBairro != null) {
            for (BairroDto item : buscaPersonalizada) {
                return ResponseEntity.ok().body(item);
            }
        }
        return ResponseEntity.ok().body(buscaPersonalizada);
    }

    @PostMapping
    public ResponseEntity<List<BairroDto>> salvar(@Valid @RequestBody BairroDto bairroDto) {
        bairroService.salvar(bairroDto);
        List<BairroDto> lista = bairroService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping
    public ResponseEntity<List<BairroDto>> atualizar(@Valid @RequestBody BairroDto bairroDto) {
        bairroService.atualizar(bairroDto);
        List<BairroDto> lista = bairroService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{codigoBairro}")
    public ResponseEntity<List<BairroDto>> deletar(@PathVariable Integer codigoBairro) {
        bairroService.deletar(codigoBairro);
        List<BairroDto> lista = bairroService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }
}
