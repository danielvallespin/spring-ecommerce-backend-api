package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dani.spring.ecommerce_backend_api.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getByUsername(String username);

    Optional<User> getById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
