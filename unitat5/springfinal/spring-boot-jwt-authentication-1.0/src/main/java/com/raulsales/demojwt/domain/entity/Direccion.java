package com.raulsales.demojwt.domain.entity;


import jakarta.persistence.*;

@Entity
@Table(name="direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "cp", nullable = false)
    private String cp;
}
