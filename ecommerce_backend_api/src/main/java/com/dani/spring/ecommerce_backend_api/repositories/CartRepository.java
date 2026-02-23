package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dani.spring.ecommerce_backend_api.entities.Cart;

public interface CartRepository extends CrudRepository<Cart, Long>{

    Optional<Cart> findByUserId(Long userId);

}
