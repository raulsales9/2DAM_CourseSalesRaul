package com.example.testraul.demo.infraestructure.persistence;

import com.example.testraul.demo.domain.entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
}