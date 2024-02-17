package com.raulsales.demojwt.domain.persistence;

import com.raulsales.demojwt.domain.entity.Cajero;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CajeroRepository extends MongoRepository<Cajero, String> {
}
