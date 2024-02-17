package com.raulsales.demojwt.domain.persistence;


import com.raulsales.demojwt.domain.entity.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
}

