/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author pc-raul
 */

import java.util.Date;


public class Jugador {

    private int id;
    
    private String nick;
    private Date dataRegistre;

    public Jugador() {}

    public Jugador(String nick, Date dataRegistre) {
        this.nick = nick;
        this.dataRegistre = dataRegistre;
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Date getDataRegistre() {
        return dataRegistre;
    }

    public void setDataRegistre(Date dataRegistre) {
        this.dataRegistre = dataRegistre;
    }

    @Override
    public String toString() {
        return "Jugador [id=" + id + ", nick=" + nick + ", dataRegistre=" + dataRegistre + "]";
    }
}

