package br.com.blog.api.api.dto.auth;

import java.time.OffsetDateTime;

public record RegisterResponseDTO(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String bio,
        OffsetDateTime createdAt
) {}