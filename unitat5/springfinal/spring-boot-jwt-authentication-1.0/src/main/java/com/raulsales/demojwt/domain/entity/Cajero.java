package com.raulsales.demojwt.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cajeros")
public class Cajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "latitud", nullable = false)
    private Double latitud;

    @Column(name = "longitud", nullable = false)
    private Double longitud;

    @Column(name = "zoom", nullable = false)
    private Integer zoom;
}
