package com.raulsales.demojwt.domain.entity;

import com.raulsales.demojwt.application.dto.AccountDTO;
import com.raulsales.demojwt.application.dto.RecommendationDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "clientes")
public class Cliente {

    @Id
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

    public List<RecommendationDTO> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendationDTO> recommendations) {
        this.recommendations = recommendations;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}

