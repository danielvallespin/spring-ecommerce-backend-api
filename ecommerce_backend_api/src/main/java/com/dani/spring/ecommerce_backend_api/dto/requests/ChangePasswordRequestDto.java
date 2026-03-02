package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;
import com.dani.spring.ecommerce_backend_api.validations.ValidPassword;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChangePasswordRequestDto {

    @Schema(description = "Password del usuario (Estricta)", example = "App12345")
    @IsRequired
    @ValidPassword
    @StringSize(max=50)
    private String password;

    public ChangePasswordRequestDto(){
    }

    public String getPassword() {
        return password;
    }


}
