package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateNewWishlistRequestDto {

    @Schema(description = "Nombre de lista", example = "Electronica")
    @IsRequired
    @StringSize(min=1, max=30)
    private String name;

    public CreateNewWishlistRequestDto(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}
