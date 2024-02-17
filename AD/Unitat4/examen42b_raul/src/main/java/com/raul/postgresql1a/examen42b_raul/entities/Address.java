/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raul.postgresql1a.examen42b_raul.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author rauls
 */@Entity
public class Address { 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public Employees getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Employees empleado) {
        this.empleado = empleado;
    }

    public Address(Long id, String calle, String numero, String manzana, Employees empleado) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.manzana = manzana;
        this.empleado = empleado;
    }

    public Address() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String calle;
    private String numero;
    private String manzana;
    @OneToOne(mappedBy = "direccion")
    private Employees empleado;
}
