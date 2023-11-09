package com.example.ad.usingorm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.OneToMany;
/**
 * 
 * @author pc-raul
 * 
 */

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String ciudad;

    @ManyToOne()
    @JoinColumn(name = "detalles_usuario_id")
    private DetallesUsuario detallesUsuario;

    @OneToOne()
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @OneToMany(mappedBy = "usuario")
    private List<Publicacion> publicaciones;

    // Constructores, getters y setters

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int edad, String ciudad, DetallesUsuario detallesUsuario, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.detallesUsuario = detallesUsuario;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public DetallesUsuario getDetallesUsuario() {
        return detallesUsuario;
    }

    public void setDetallesUsuario(DetallesUsuario detallesUsuario) {
        this.detallesUsuario = detallesUsuario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
