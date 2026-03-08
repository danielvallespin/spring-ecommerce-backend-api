package com.dani.spring.ecommerce_backend_api.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserResponseDto {

    @Schema(description = "Id del usuario", example = "5")
    private final Long userId;

    @Schema(description = "username del usuario", example = "dani")
    private final String username;

    @Schema(description = "Email del usuario", example = "dani@email.com")
    private final String email;

    public UserResponseDto(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public Long getUserId(){
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }



}
