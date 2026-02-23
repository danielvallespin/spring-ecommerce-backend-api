package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.UserAdminRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.UserAdminResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.UserRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.UserResponseDto;
import com.dani.spring.ecommerce_backend_api.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            @ApiResponse(
                responseCode = "200", 
                description = "Lista de usuarios obtenida correctamente", 
                content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserAdminResponseDto.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserAdminResponseDto> list(){
        return service.getAll();
    }

    //GET_BY_ID
    @Operation(summary = "Obtener usuario por id (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserAdminResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto indicado", content = @Content),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserAdminResponseDto> getUserById(@PathVariable Long id){
        UserAdminResponseDto userResponse = service.getById(id);
        if (userResponse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponse);
    }

    //CREATE (ADMIN)
    @Operation(summary = "Crear usuario con capacidad de hacerlo admin (solo para admins)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserAdminResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserAdminRequestDto user){
        UserResponseDto newUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    //CREATE (NORMAL)
    @Operation(summary = "Crear usuario normal (SIN JWT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación", content = @Content)
    })
    @PostMapping("/singup")
    public ResponseEntity<?> singup(@Valid @RequestBody UserRequestDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }


    //GET_YOU_USER
    @Operation(summary = "Obtener tu propio usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
    })
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyUser(Principal principal){
        return ResponseEntity.ok(service.getMyUser(principal.getName()));
    }



}
