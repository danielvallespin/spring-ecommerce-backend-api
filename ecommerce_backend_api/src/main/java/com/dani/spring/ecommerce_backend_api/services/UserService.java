package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.dto.requests.UserRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.User;

public interface UserService {

    List<User> getAllusers();

    Optional<User> getUserById(Long id);

    User saveUser(UserRequestDto user);

    Optional<User> getMyUserByUsername(String username);

    void deleteUserById(Long id);

    User saveUser(User user);

    public String encodePasswd(String str);

}
