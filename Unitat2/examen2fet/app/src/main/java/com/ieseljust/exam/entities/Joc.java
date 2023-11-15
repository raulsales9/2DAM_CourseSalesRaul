package com.mycompany.entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc-raul
 */
public class Joc{
    private int id;
    private String nom;
    private String descripcio;
    private int genere_id;
    
       /*
    *
    constructor buit
    **/
    public Joc(){}
    
    public Joc(int id, String nom, String descripció, int genere_id) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripció;
        this.genere_id = genere_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getGenere_id() {
        return genere_id;
    }

    public void setGenere_id(int genere_id) {
        this.genere_id = genere_id;
    }
    @Override
    public String toString() {
        return "Joc [id=" + id + ", nom=" + nom + ", descripcio=" + descripcio + ", genereId=" + genere_id + "]";
    }

}
