package com.dani.spring.ecommerce_backend_api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
                        content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", 
                        description = "Credenciales inválidas",
                        content = @Content)
    })
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        // Este método NO se ejecuta realmente
        // Solo está aquí para documentación de Swagger
        // El filtro JwtAuthenticationFilter intercepta esta ruta
        throw new IllegalStateException("Este método no debería ser llamado. Es manejado por JwtAuthenticationFilter");
    }

    // DTOs para documentación
    @Schema(description = "Credenciales de login")
    public static class LoginRequest {
        @Schema(description = "Nombre de usuario", example = "admin")
        private String username;
        
        @Schema(description = "Contraseña", example = "admin")
        private String password;

        // Getters y setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @Schema(description = "Respuesta de login exitoso")
    public static class LoginResponse {
        @Schema(description = "Token JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        private String token;
        
        @Schema(description = "Nombre de usuario autenticado", example = "admin")
        private String username;
        
        @Schema(description = "Mensaje de éxito", example = "Login exitoso")
        private String message;

        // Getters y setters
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}