package com.dani.spring.ecommerce_backend_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserAdminRequestDto extends UserRequestDto{

    @Schema(description = "El usuario es admin (Opcional, por defecto false)", example = "false")
    private Boolean admin;

    @Schema(description = "El usuario esta activado (Opcional, por defecto true)", example = "true")
    private boolean enabled;


    public boolean isEnabled() {
        return enabled;
    }

    public Boolean isAdmin() {
        return admin;
    }


}
