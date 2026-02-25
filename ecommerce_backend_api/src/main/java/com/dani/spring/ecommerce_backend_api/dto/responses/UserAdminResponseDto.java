package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.util.List;

public class UserAdminResponseDto extends UserResponseDto{

    private final Boolean enabled;

    private final List<String> roles;

    public UserAdminResponseDto(Long id, String username, String email, Boolean enabled, List<String> roles) {
        super(id, username, email);
        this.enabled = enabled;
        this.roles = roles;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    

}
