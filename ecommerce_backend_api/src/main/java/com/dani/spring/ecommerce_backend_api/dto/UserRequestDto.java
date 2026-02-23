package com.dani.spring.ecommerce_backend_api.dto;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public class UserRequestDto {

    @Schema(description = "Username del usuario", example = "dani")
    @IsRequired
    private String username;

    @Schema(description = "Password del usuario", example = "1234")
    @IsRequired
    @StringSize(min=8, max=20)
    private String password;

    @Schema(description = "Email del usuario", example = "dani@email.com")
    @IsRequired
    @Email
    private String email;

    
    public UserRequestDto() {}

    public UserRequestDto(String username, String password, @Email String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    

}
