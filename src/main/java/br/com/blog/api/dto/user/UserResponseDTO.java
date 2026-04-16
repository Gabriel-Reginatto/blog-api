package br.com.blog.api.dto.user;

import java.time.OffsetDateTime;

public record UserResponseDTO(

        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String bio,
        OffsetDateTime createdAt
){}
