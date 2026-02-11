package com.dani.spring.ecommerce_backend_api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dani.spring.ecommerce_backend_api.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
