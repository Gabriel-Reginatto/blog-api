package br.com.blog.api.core.enums;

import br.com.blog.api.infrastructure.security.SecurityConstants;

public enum UserRole {

    ROLE_ADMIN(SecurityConstants.ROLE_ADMIN),
    ROLE_USER(SecurityConstants.ROLE_USER);

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole fromString(String role) {
        for (UserRole r : UserRole.values()) {
            if (r.role.equalsIgnoreCase(role) || r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }
        return ROLE_USER;

    }

}
