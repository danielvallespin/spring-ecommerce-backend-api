package com.dani.spring.ecommerce_backend_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.entities.User;
import com.dani.spring.ecommerce_backend_api.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    //GET_ALL
    @Operation(summary = "Obtener todos los usuarios del sistema (solo para admins)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> list(){
        return service.getAll();
    }

    //CREATE
    @Operation(summary = "Crear usuario con capacidad de hacerlo admin (solo para admins)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

}
