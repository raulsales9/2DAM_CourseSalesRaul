/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordatabase_raul1b.entity;

import javax.persistence.Entity;

/**
 *
 * @author rauls
 */
@Entity
public class Sellers extends Employee {

    @Override
    public String toString() {
        return "Sellers{" + "comisionPorVenta=" + comisionPorVenta + ", funcion=" + funcion + '}';
    }

    public Double getComisionPorVenta() {
        return comisionPorVenta;
    }

    public void setComisionPorVenta(Double comisionPorVenta) {
        this.comisionPorVenta = comisionPorVenta;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Sellers(Double comisionPorVenta, String funcion) {
        this.comisionPorVenta = comisionPorVenta;
        this.funcion = funcion;
    }

    public Sellers() {
    }
    private Double comisionPorVenta;
    private String funcion;


}
