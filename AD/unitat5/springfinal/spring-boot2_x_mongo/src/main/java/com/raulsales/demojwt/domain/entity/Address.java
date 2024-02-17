package com.raulsales.demojwt.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

public class Address {
    private String cp;
    private String descripcion;
    private String pais;

}
