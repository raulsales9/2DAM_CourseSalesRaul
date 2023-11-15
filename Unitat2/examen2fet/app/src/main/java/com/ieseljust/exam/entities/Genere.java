/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author pc-raul
 */
public class Genere {
    private int id;

    private Date datasegistre;

    public Genere() {}

    public Genere(Date datasegistre) {
        this.datasegistre = datasegistre;
    }

    // Métodos getter y setter para cada campo
    public int getId() {
        return id;
    }

    public Date getDatasegistre() {
        return datasegistre;
    }

    public void setDatasegistre(Date datasegistre) {
        this.datasegistre = datasegistre;
    }

    @Override
    public String toString() {
        return "Puntuacions [id=" + id + ", datasegistre=" + datasegistre + "]";
    }
}