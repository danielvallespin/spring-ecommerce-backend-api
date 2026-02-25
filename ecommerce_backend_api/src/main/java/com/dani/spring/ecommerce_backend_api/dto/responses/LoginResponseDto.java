package com.dani.spring.ecommerce_backend_api.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de login exitoso")
public class LoginResponseDto {

    @Schema(description = "Token JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private final String token;

    @Schema(description = "Nombre de usuario autenticado", example = "admin")
    private final String username;

    @Schema(description = "Mensaje de éxito", example = "Login exitoso")
    private final String message;

    public LoginResponseDto(String token, String username, String message) {
        this.token = token;
        this.username = username;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    
}
