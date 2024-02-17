package com.examenraulsales.examenraulhibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author rauls
 */

@Entity
public class Jugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJugador")
    private long idJugador;
    
    @Column(name = "nombre")
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name= "idEquipoJuega", nullable=false)
    private Equipo equipo;
            
    @ManyToOne
    @JoinColumn(name= "idCompeticionJuega", nullable=false)
    private Competicion competicion;
    
    public Jugar() {
    }
    
    public Jugar(long idJugador, String nombre, Equipo equipo, Competicion competicion) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.equipo = equipo;
        this.competicion = competicion;
    }

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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }
}
