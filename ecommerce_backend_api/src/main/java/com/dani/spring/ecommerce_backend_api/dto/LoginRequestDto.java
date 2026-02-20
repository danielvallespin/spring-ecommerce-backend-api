package com.dani.spring.ecommerce_backend_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credenciales de login")
public class LoginRequestDto {

    @Schema(description = "Nombre de usuario", example = "admin")
    private String username;

    @Schema(description = "Contraseña", example = "admin")
    private String password;

    public LoginRequestDto(){}

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
