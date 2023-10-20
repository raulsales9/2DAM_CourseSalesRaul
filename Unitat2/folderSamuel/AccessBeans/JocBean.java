/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.AccessBeans;

/**
 *
 * @author samuel
 */
public class JocBean {
    private int pkid;
    private String name;
    private String description;
    private int genere;

    public int getPkid() {
        return pkid;
    }

    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGenere() {
        return genere;
    }

    public void setGenere(int genere) {
        this.genere = genere;
    }

    public JocBean() {
    }

    public JocBean(int pkid, String name, String description, int genere) {
        this.pkid = pkid;
        this.name = name;
        this.description = description;
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "JocBean{" + "pkid=" + pkid + ", name=" + name + ", description=" + description + ", genere=" + genere + '}';
    }
    

}
