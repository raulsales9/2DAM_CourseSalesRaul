package jaume.ad.aplicattion.dto;

import jaume.ad.domain.model.Equipo;

public class JugadorDto {
    private long idJugador;
    private String nombre;

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo.getIdEquipo();
    }

    private long idEquipo;


    public long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(long idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
