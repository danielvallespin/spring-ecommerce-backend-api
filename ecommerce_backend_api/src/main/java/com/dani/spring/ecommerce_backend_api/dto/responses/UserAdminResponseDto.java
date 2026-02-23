package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.util.List;

public class UserAdminResponseDto extends UserResponseDto{

    private boolean enabled;

    private List<String> roles;

    public UserAdminResponseDto(String username, String email, boolean enabled, List<String> roles) {
        super(username, email);
        this.enabled = enabled;
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    

}
