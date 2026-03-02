package com.dani.spring.ecommerce_backend_api.services;

import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;
import com.dani.spring.ecommerce_backend_api.entities.user.User;

public interface CartService {

    Optional<Cart> getUserCart(String username);

    Cart createCart(User user);

}
