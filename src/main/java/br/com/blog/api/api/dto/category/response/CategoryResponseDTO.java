package br.com.blog.api.api.dto.category.response;

import org.springframework.hateoas.server.core.Relation;

import java.time.OffsetDateTime;

@Relation(collectionRelation = "Categories", itemRelation = "category")
public record CategoryResponseDTO(
        Long id,
        String name,
        String description,
        OffsetDateTime createdAt,
        Long postCount
){}





