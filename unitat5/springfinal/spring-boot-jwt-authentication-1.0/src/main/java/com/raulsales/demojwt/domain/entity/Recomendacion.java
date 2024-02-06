package com.raulsales.demojwt.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "observaciones", nullable = false)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "idcliente", unique = true, nullable = false)
    private Cliente cliente;
}
