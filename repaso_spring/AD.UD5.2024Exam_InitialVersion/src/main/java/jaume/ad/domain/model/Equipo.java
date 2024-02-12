package jaume.ad.domain.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Equipo")
public class Equipo {
    @Id
    @GeneratedValue
    private long idEquipo;

    @Column
    private String nombre;
    @Column
    private String ciudad;
    @OneToMany(mappedBy="equipo")
    private Set<Jugador> jugadores;

    @ManyToMany(mappedBy="equipos")
    private Set<Competicion> competiciones;

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Set<Competicion> getCompeticiones() {
        return competiciones;
    }

    public void setCompeticiones(Set<Competicion> competiciones) {
        this.competiciones = competiciones;
    }
}
