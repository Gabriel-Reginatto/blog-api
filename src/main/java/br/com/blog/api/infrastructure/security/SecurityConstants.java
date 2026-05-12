package br.com.blog.api.infrastructure.security;

public final class SecurityConstants {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String[] PUBLIC_URLS = {
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/refresh",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    private SecurityConstants() {}
}
