package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.role.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(String name);

}
