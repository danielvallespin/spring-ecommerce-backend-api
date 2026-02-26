package com.dani.spring.ecommerce_backend_api.services;

import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;

public interface CartService {

    Optional<Cart> getUserCart(String username);

}
