package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

import br.com.squadra.bootcamp.desafiofinal.rafaelsouza.entities.Bairro;

public class BairroGetDto extends BairroDto {
    private static final long serialVersionUID = 1L;

    private MunicipioDto municipio;

    public BairroGetDto(Bairro entity, MunicipioDto municipioDto) {
        super(entity);
        this.municipio = municipioDto;
    }

    public BairroGetDto(Integer codigoBairro, Integer codigoMunicipio, String nome, Integer status, MunicipioDto municipio) {
        super(codigoBairro, codigoMunicipio, nome, status);
        this.municipio = municipio;
    }

    public MunicipioDto getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioDto municipio) {
        this.municipio = municipio;
    }
}