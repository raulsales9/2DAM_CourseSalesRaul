

package com.example.ad.usingorm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pc-raul
 */

@Entity
public class DetallesUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String telefono;

    @Column
    private String email;

    // Constructores, getters y setters

    public DetallesUsuario() {
    }

    public DetallesUsuario(String telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
