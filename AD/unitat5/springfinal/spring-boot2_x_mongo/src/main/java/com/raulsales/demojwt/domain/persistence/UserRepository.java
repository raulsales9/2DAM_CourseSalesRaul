package com.raulsales.demojwt.domain.persistence;

import com.raulsales.demojwt.domain.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Usuario, String> {
    Usuario findByUsername(String username);
}
