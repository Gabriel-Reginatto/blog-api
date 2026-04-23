package br.com.blog.api.api.dto.user.response;

import org.springframework.hateoas.server.core.Relation;

import java.time.OffsetDateTime;

@Relation(collectionRelation = "User")
public record UserResponseDTO(

        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String bio,
        OffsetDateTime createdAt
){}
