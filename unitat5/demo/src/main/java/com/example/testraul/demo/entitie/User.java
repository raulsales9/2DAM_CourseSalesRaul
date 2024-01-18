package com.example.testraul.demo.entitie;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "User")
public class User {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "Nombre")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "FechaRegistro")
    private Date fechaRegistro;
}
