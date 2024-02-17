/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenraulsales.examenraulhibernate.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author rauls
 */
@Entity
public class Entrenador {

    public Entrenador(Long id, String name, int puntuacion, int posicion, int stats, Equipo equipo, List<Jugador> jugadores, List<Equipo> equipos) {
        this.id = id;
        this.name = name;
        this.puntuacion = puntuacion;
        this.posicion = posicion;
        this.stats = stats;
        this.equipo = equipo;
        this.jugadores = jugadores;
        this.equipos = equipos;
    }

    public Entrenador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getStats() {
        return stats;
    }

    public void setStats(int stats) {
        this.stats = stats;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
    @Id
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private int puntuacion;
    
    @Column
    private int posicion;
    
    @Column
    private int stats;
    
    @OneToOne
    private Equipo equipo;
    
    @ManyToMany
    private List<Jugador> jugadores;
    
    @OneToMany(mappedBy="entrenador")
    private List<Equipo> equipos;
 
    } 

