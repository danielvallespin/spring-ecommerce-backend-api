package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.dto.requests.UserRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.UserAdminResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.UserResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.User;

public interface UserService {

    List<UserAdminResponseDto> getAllusers();

    UserAdminResponseDto getUserResponseById(Long id);

    Optional<User> getUserById(Long id);

    UserResponseDto saveUser(UserRequestDto user);

    UserResponseDto getMyUserResponse(String username);

    Optional<User> getMyUser(String username);

    void deleteUserById(Long id);

}
