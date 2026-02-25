package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
