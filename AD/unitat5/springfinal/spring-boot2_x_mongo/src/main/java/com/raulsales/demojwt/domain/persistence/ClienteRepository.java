package com.raulsales.demojwt.domain.persistence;

import com.raulsales.demojwt.domain.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
