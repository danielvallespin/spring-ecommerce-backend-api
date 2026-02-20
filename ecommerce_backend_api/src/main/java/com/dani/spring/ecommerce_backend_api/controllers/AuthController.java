package com.dani.spring.ecommerce_backend_api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.LoginRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.LoginResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Autenticación", description = "Endpoints de autenticación y autorización")
public class AuthController {

    @Operation(summary = "Login de usuario", 
               description = "Autentica un usuario y retorna un token JWT en el header 'Authorization' y en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", 
                        description = "Login exitoso - Token en header 'Authorization' y en el body",
                        content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "401", 
                        description = "Credenciales inválidas",
                        content = @Content)
    })
    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto loginRequest) {
        // Este método NO se ejecuta realmente está aquí para documentación de Swagger
        // El filtro JwtAuthenticationFilter intercepta esta ruta y ejecuta la creacion del token
        throw new IllegalStateException("Este método no debería ser llamado. Es manejado por JwtAuthenticationFilter");
    }

}