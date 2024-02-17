package com.example.testraul.demo.aplication.dtos;

import java.util.Date;

public class userDto {
    private Long id;
    private String name;
    private String email;
    private Date fechaRegistro;

    // Constructores

    public userDto() {
        // Constructor por defecto necesario para Thymeleaf
    }

    public userDto(Long id, String name, String email, Date fechaRegistro) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    // Métodos getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
