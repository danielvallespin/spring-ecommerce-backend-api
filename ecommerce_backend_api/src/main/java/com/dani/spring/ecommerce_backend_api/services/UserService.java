package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.UserAdminResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.UserRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.UserResponseDto;

public interface UserService {

    List<UserAdminResponseDto> getAll();

    UserAdminResponseDto getById(Long id);

    UserResponseDto save(UserRequestDto user);

    UserResponseDto getMyUser(String username);

}
