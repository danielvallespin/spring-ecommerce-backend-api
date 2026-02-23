package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;
import com.dani.spring.ecommerce_backend_api.validations.ValidPassword;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public class UserRequestDto {

    @Schema(description = "Username del usuario", example = "dani")
    @IsRequired
    @StringSize(min=3, max=25)
    private String username;

    @Schema(description = "Password del usuario (Estricta)", example = "App12345")
    @IsRequired
    @ValidPassword
    @StringSize(max=50)
    private String password;

    @Schema(description = "Email del usuario", example = "dani@email.com")
    @IsRequired
    @StringSize(max=100)
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
