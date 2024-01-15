package com.example.testraul.demo.repository;

import com.example.testraul.demo.entitie.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}