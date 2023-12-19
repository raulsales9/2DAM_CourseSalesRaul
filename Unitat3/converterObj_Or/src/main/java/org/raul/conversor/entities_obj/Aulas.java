/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.raul.conversor.entities_obj;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author rauls
 */
@Entity
@Table(name = "Aulas")
public class Aulas {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    public Aulas(int id, String nombre, int capacidad, Asignaturas asignatura) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.asignatura = asignatura;
    }

    public Aulas() {
    }
    @Id
    private int id;
    private String nombre;
    private int capacidad;
    @ManyToOne
    @JoinColumn(name = "AsignaturaID")
    private Asignaturas asignatura;
    @OneToMany(mappedBy = "aula")
    private List<Horarios> horarios;
}
