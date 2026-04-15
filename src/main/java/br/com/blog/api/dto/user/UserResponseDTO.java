package br.com.blog.api.dto.user;

import java.time.OffsetDateTime;

public record UserResponseDTO(

        Long id,
        String username,
        String email,
        String fullName,
        String bio,
        OffsetDateTime createdAt
){}
