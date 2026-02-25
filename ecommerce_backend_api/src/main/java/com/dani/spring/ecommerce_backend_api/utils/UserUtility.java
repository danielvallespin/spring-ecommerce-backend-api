package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.dto.responses.UserAdminResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.UserResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.Role;
import com.dani.spring.ecommerce_backend_api.entities.User;

import jakarta.persistence.EntityNotFoundException;

public class UserUtility {

    /**
     * Metodo para obtener objeto dto de user normal
     * @param user
     * @return UserResponseDto
     */
    public static UserResponseDto getUserResponse(User user){
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    /**
     * Metodo para obtener un lista de la respuesta dto de user admin
     *
     * @param users
     * @return List<UserAdminResponseDto>
     */
    public static List<UserAdminResponseDto> getListOfUserAdminResponse(List<User> users) {
        List<UserAdminResponseDto> response = new ArrayList<>();
        for (User user : users) {
            response.add(getUserAdminResponse(user));
        }

        return response;
    }

    /**
     * Metodo para obtener un objeto de respuesta dto de user admin
     *
     * @param user
     * @return UserAdminResponseDto
     */
    public static UserAdminResponseDto getUserAdminResponse(User user) {
        return new UserAdminResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled(),
                getListRolesOfUser(user.getRoles()));
    }

    /**
     * Metodo para obtener una lista con los roles de un usuario
     *
     * @param roles
     * @return List<String>
     */
    private static List<String> getListRolesOfUser(List<Role> roles) {
        List<String> rolesList = new ArrayList<>();
        for (Role role : roles) {
            rolesList.add(role.getName());
        }

        return rolesList;
    }


    /**
     * Metodo que devuelve un objeto User de un optional, sino lanza error 404 a traves de la clase GlobalExceptionHandler
     * @param optUser
     * @param id
     * @return User
     */
    public static User getUserFromOptionalOrThrow(Optional<User> optUser, Long id){
        return optUser.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningún usuario con id: " + id));
    }

}
