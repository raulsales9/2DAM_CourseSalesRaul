/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenraulsales.examenraulhibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author rauls
 */

@Entity
@Table(name = "Competicion")
public class Competicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompeticion")
    private Long idCompeticion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "premio")
    private Double premio;
    
    public Competicion() {
    }

    public Competicion(Long idCompeticion, String nombre, Double premio) {
        this.idCompeticion = idCompeticion;
        this.nombre = nombre;
        this.premio = premio;
    }

    
    public Long getIdCompeticion() {
        return idCompeticion;
    }

    public void setIdCompeticion(Long idCompeticion) {
        this.idCompeticion = idCompeticion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPremio() {
        return premio;
    }

    public void setPremio(Double premio) {
        this.premio = premio;
    }
}
