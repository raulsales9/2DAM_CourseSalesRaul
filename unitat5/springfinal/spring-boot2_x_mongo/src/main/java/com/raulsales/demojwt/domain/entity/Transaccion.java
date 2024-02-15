package com.raulsales.demojwt.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "transacciones")
public class Transaccion {

    @Id
    private String _id;

    private double monto;
    private Date fecha;
    private String tipo;
    private String descripcion;
    private Location location;

}

