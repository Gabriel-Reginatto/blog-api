package br.com.blog.api.dto.category;

import java.time.OffsetDateTime;

public record CategoryResponseDTO(

        Long id,
        String name,
        String description,
        OffsetDateTime createdAt,
        Long postCount
) {}
