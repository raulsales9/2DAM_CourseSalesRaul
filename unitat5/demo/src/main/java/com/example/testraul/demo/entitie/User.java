package com.example.testraul.demo.entitie;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User")
public class User {
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
