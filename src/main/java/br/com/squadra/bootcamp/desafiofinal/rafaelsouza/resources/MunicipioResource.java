package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.resources;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos.MunicipioDto;
import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.services.MunicipioService;
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
@RequestMapping("/municipio")
public class MunicipioResource {

    private MunicipioService municipioService;

    public MunicipioResource(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping
    public ResponseEntity<List<MunicipioDto>> buscarTodos() {
        List<MunicipioDto> lista = municipioService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(params = "codigoMunicipio")
    public ResponseEntity<MunicipioDto> buscarPeloCodigoMunicipio(@RequestParam Integer codigoMunicipio) {
        MunicipioDto municipioDto = municipioService.buscarPeloCodigoMunicipio(codigoMunicipio);
        return ResponseEntity.ok().body(municipioDto);
    }

    @GetMapping(params = "codigoUF")
    public ResponseEntity<List<MunicipioDto>> buscarTodosPeloCodigoUF(@RequestParam Integer codigoUF) {
        List<MunicipioDto> lista = municipioService.buscarTodosPeloCodigoUF(codigoUF);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(params = "nome")
    public ResponseEntity<MunicipioDto> buscarPeloNome(@RequestParam String nome) {
        MunicipioDto uf = municipioService.buscarPelaNome(nome);
        return ResponseEntity.ok().body(uf);
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<MunicipioDto>> buscarPeloStatus(@RequestParam Integer status) {
        List<MunicipioDto> uf = municipioService.buscarPeloStatus(status);
        return ResponseEntity.ok().body(uf);
    }

    @PostMapping
    public ResponseEntity<List<MunicipioDto>> salvar(@Valid @RequestBody MunicipioDto municipioDto) {
        municipioService.salvar(municipioDto);
        List<MunicipioDto> lista = municipioService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping
    public ResponseEntity<List<MunicipioDto>> atualizar(@Valid @RequestBody MunicipioDto municipioDto) {
        municipioService.atualizar(municipioDto);
        List<MunicipioDto> lista = municipioService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{codigoMunicipio}")
    public ResponseEntity<List<MunicipioDto>> deletar(@PathVariable Integer codigoMunicipio) {
        municipioService.deletar(codigoMunicipio);
        List<MunicipioDto> lista = municipioService.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }
}
