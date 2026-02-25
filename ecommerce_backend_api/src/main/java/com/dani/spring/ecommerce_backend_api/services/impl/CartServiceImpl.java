package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.entities.Cart;
import com.dani.spring.ecommerce_backend_api.entities.User;
import com.dani.spring.ecommerce_backend_api.repositories.CartRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository repository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public Optional<Cart> getUserCart(String username){
        User user = userRepository.getByUsername(username).orElseThrow();
        return repository.findByUserId(user.getId());
    }




}
