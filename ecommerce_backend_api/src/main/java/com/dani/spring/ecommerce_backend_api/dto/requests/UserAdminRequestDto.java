package com.dani.spring.ecommerce_backend_api.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserAdminRequestDto extends UserRequestDto{

    @Schema(description = "El usuario es admin (Opcional, por defecto false)", example = "false")
    private Boolean admin;

    @Schema(description = "El usuario esta activado (Opcional, por defecto true)", example = "true")
    private Boolean enabled;

    public UserAdminRequestDto(){
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public Boolean isAdmin() {
        return admin;
    }


}
