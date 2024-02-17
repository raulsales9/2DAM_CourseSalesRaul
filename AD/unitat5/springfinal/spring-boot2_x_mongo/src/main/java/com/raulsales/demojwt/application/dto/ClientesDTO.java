package com.raulsales.demojwt.application.dto;

import com.raulsales.demojwt.domain.entity.Account;
import com.raulsales.demojwt.domain.entity.Recommendation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientesDTO {

    private String _id;
    private String apellidos;
    private String claveseguridad;
    private String email;
    private String nif;
    private String nombre;
    private List<AccountDTO> accounts;
    private List<RecommendationDTO> recommendations;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public List<RecommendationDTO> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendationDTO> recommendations) {
        this.recommendations = recommendations;
    }
}
