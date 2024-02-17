/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordatabase_raul1b.entity;

import javax.persistence.Entity;
import ordatabase_raul1b.entity.Employee;
/**
 *
 * @author rauls
 */

@Entity
public class Commercial extends Employee {

    @Override
    public String toString() {
        return "Commercial{" + "remuneracion=" + remuneracion + ", zonaDeActuacion=" + zonaDeActuacion + '}';
    }

    public Commercial(Double remuneracion, String zonaDeActuacion) {
        this.remuneracion = remuneracion;
        this.zonaDeActuacion = zonaDeActuacion;
    }

    public Commercial() {
    }

    public Double getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(Double remuneracion) {
        this.remuneracion = remuneracion;
    }

    public String getZonaDeActuacion() {
        return zonaDeActuacion;
    }

    public void setZonaDeActuacion(String zonaDeActuacion) {
        this.zonaDeActuacion = zonaDeActuacion;
    }
    private Double remuneracion;
    private String zonaDeActuacion;

}

