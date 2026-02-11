package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dani.spring.ecommerce_backend_api.entities.Role;


public interface RoleRepository extends CrudRepository<Role, Long>{

    Optional<Role> findByName(String name);

}
