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
    public ResponseEntity<?> buscarPorParametros(
            @RequestParam(value = "codigoMunicipio", required = false) String codigoMunicipio,
            @RequestParam(value = "codigoUF", required = false) String codigoUF,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) String status
    ) {
        List<MunicipioDto> buscaPersonalizada = municipioService.buscarPorParametros(codigoMunicipio, codigoUF, nome, status);
        if (codigoMunicipio != null) {
            for (MunicipioDto item : buscaPersonalizada) {
                return ResponseEntity.ok().body(item);
            }
        }
        return ResponseEntity.ok().body(buscaPersonalizada);
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
