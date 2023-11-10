package com.example.ad.usingorm.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


/**
 * 
 * @author pc-raul
 * 
 */
@Entity
@Table(name = "publicaciones")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String contenido;

    @Column
    private Date fechaPublicacion;

    @ManyToOne()
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    public Publicacion() {
    }

    public Publicacion(String contenido, Date fechaPublicacion, Usuario usuario) {
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
