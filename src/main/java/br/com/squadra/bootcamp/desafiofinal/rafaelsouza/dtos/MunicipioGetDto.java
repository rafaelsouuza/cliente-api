package br.com.squadra.bootcamp.desafiofinal.rafaelsouza.dtos;

public class MunicipioGetDto extends MunicipioDto {
    private static final long serialVersionUID = 1L;

    private UFDto uf;

    public MunicipioGetDto(Integer codigoMunicipio, Integer codigoUF, String nome, Integer status, UFDto uf) {
        super(codigoMunicipio, codigoUF, nome, status);
        this.uf = uf;
    }

    public UFDto getUf() {
        return uf;
    }

    public void setUf(UFDto uf) {
        this.uf = uf;
    }
}
