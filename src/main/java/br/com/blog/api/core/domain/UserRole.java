package br.com.blog.api.core.domain;

import br.com.blog.api.infrastructure.security.SecurityConstants;

public enum UserRole {

    ADMIN(SecurityConstants.ROLE_ADMIN),
    USER(SecurityConstants.ROLE_USER);

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
        return USER;
    }

}
