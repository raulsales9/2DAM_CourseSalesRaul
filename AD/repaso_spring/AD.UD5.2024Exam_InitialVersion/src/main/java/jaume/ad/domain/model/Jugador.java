package jaume.ad.domain.model;

import javax.persistence.*;

@Entity
@Table(name="Jugador")
public class Jugador {
    @Id
    @GeneratedValue
    private long idJugador;
    @Column
    private String nombre;
    @ManyToOne
    @JoinColumn(name="idEquipo", nullable = false)
    private Equipo equipo;

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

    public Equipo getIdEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
