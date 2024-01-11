/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raul.postgresql1a.examen42b_raul.entities;

import java.beans.Transient;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author rauls
 */
@Entity
public class Employees {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Projects> getManagedProjects() {
        return managedProjects;
    }

    public void setManagedProjects(Set<Projects> managedProjects) {
        this.managedProjects = managedProjects;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    public Employees(Long id, String nombre, Date fechaContratacion, Departments department, Address address, Set<Projects> managedProjects, Set<Projects> projects) {
        this.id = id;
        this.nombre = nombre;
        this.fechaContratacion = fechaContratacion;
        this.department = department;
        this.address = address;
        this.managedProjects = managedProjects;
        this.projects = projects;
    }

    public Employees() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Date fechaContratacion;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy="manager")
    private Set<Projects> managedProjects;

    @ManyToMany(mappedBy = "employees")
    private Set<Projects> projects;
    
}
