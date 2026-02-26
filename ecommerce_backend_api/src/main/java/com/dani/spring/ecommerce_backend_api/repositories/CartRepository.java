package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

    Optional<Cart> findByUserId(Long userId);

}
