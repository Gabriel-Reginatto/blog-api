package br.com.blog.api.api.dto.category.response;

import java.time.OffsetDateTime;

public record CategoryResponseDTO(

        Long id,
        String name,
        String description,
        OffsetDateTime createdAt,
        Long postCount
) {}
