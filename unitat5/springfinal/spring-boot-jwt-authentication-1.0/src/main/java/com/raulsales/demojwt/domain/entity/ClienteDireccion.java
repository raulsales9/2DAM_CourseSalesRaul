package com.raulsales.demojwt.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="clientesdirecciones")
public class ClienteDireccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "iddireccion", nullable = false)
    private Direccion direccion;
}
