package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.requests.UserRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.user.User;

public interface UserService {

    List<User> getAllusers();

    User getUserById(Long userId);

    User createUser(UserRequestDto user);

    User getUserByUsername(String username);

    void deleteUserById(Long userId);

    User saveUser(User user);

    String encodePasswd(String str);

    boolean isAdmin(String username);

}
