package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.BairroDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.MunicipioDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.BairroService;
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
@RequestMapping("/bairro")
public class BairroResource {

    private BairroService bairroService;

    public BairroResource(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    @GetMapping
    public ResponseEntity<List<BairroDto>> buscarTodos() {
        List<BairroDto> lista = bairroService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(params = "codigoBairro")
    public ResponseEntity<BairroDto> buscarPeloCodigoBairro(@RequestParam Integer codigoBairro) {
        BairroDto bairroDto = bairroService.buscarPeloCodigoBairro(codigoBairro);
        return ResponseEntity.ok().body(bairroDto);
    }

    @GetMapping(params = "codigoMunicipio")
    public ResponseEntity<List<BairroDto>> buscarTodosPeloMunicipio(@RequestParam Integer codigoMunicipio) {
        List<BairroDto> lista = bairroService.buscarPeloCodigoMunicipio(codigoMunicipio);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(params = "nome")
    public ResponseEntity<BairroDto> buscarPeloNome(@RequestParam String nome) {
        BairroDto bairro = bairroService.buscarPelaNome(nome);
        return ResponseEntity.ok().body(bairro);
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<BairroDto>> buscarPeloStatus(@RequestParam Integer status) {
        List<BairroDto> uf = bairroService.buscarPeloStatus(status);
        return ResponseEntity.ok().body(uf);
    }

    @PostMapping
    public ResponseEntity<List<BairroDto>> salvar(@Valid @RequestBody BairroDto bairroDto) {
        bairroService.salvar(bairroDto);
        List<BairroDto> lista = bairroService.buscarTodos();
        return ResponseEntity.status(HttpStatus.CREATED).body(lista);
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
