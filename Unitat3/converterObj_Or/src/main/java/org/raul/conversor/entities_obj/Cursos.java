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
@Table(name = "Cursos")
public class Cursos {
    @Id
    private int id;
    private String nombre;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "EstudianteID")
    private Estudiantes estudiante;
    @ManyToOne
    @JoinColumn(name = "ProfesorID")
    private Profesor profesor;
    @OneToMany(mappedBy = "curso")
    private List<Asignaturas> asignaturas;
    
    
    public Cursos(int id, String nombre, String descripcion, Estudiantes estudiante, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estudiante = estudiante;
        this.profesor = profesor;
    }

    public Cursos() {
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
