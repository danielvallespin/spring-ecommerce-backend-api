package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.entities.User;

public interface UserService {

    List<User> getAll();

    User save(User user);

}
