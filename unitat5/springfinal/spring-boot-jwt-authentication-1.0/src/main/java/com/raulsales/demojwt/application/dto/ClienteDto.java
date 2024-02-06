package com.raulsales.demojwt.application.dto;

import java.util.List;

public class ClienteDto {
    private Long id;
    private String nif;
    private String nombre;
    private String apellidos;
    private String claveseguridad;
    private String email;
    private List<CuentaDto> cuentas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getClaveseguridad() {
        return claveseguridad;
    }

    public void setClaveseguridad(String claveseguridad) {
        this.claveseguridad = claveseguridad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CuentaDto> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaDto> cuentas) {
        this.cuentas = cuentas;
    }
}
