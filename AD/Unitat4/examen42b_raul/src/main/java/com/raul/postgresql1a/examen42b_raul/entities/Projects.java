/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raul.postgresql1a.examen42b_raul.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author rauls
 */
@Entity
public class Projects {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Employees getDirector() {
        return director;
    }

    public void setDirector(Employees director) {
        this.director = director;
    }

    public Set<Employees> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Employees> empleados) {
        this.empleados = empleados;
    }

    public Projects(Long id, String descripcion, Employees director, Set<Employees> empleados) {
        this.id = id;
        this.descripcion = descripcion;
        this.director = director;
        this.empleados = empleados;
    }

    public Projects() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descripcion;
    @ManyToOne
    private Employees director;
    @ManyToMany
    @JoinTable(name = "proyecto_empleado",
        joinColumns = @JoinColumn(name = "proyecto_id"),
        inverseJoinColumns = @JoinColumn(name = "empleado_id"))
    private Set<Employees> empleados;
}

