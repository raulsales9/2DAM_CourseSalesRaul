/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author pc-raul
 */
public class Puntuacions {
    private int jocId;
    private int jugadorId; 
    private int puntuacio;
    public Puntuacions() {}

    public Puntuacions(int jocId, int jugadorId, int puntuacio) {
        this.jocId = jocId;
        this.jugadorId = jugadorId;
        this.puntuacio = puntuacio;
    }

    public int getJocId() {
        return jocId;
    }

    public void setJocId(int jocId) {
        this.jocId = jocId;
    }
    
    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    @Override
    public String toString() {
        return "Puntuacions [jocId=" + jocId + ", jugadorId=" + jugadorId + ", puntuacio=" + puntuacio + "]";
    }
}
