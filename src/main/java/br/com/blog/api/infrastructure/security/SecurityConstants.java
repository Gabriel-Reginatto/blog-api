package br.com.blog.api.infrastructure.security;

public final class SecurityConstants {

    // Roles
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    // Headers
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    // Public endpoints
    public static final String[] PUBLIC_URLS = {
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/refresh",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    // Admin only endpoints
    public static final String[] ADMIN_URLS = {
            "/api/v1/categories/**",

    };

    private SecurityConstants() {}
}
