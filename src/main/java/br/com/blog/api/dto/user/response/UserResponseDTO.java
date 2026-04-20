package br.com.blog.api.dto.user.response;

import org.springframework.hateoas.RepresentationModel;

public record UserResponseDTO extends RepresentationModel<UserResponseDTO>(

        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String bio,
        OffsetDateTime createdAt
){}
