package com.raulsales.demojwt.domain.entity;


import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String _id;

    private String country;
    private String firstname;
    private String lastname;
    private String password;
    private String username;
    private String role;


}

