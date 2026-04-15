package br.com.blog.api.dto.post;

import br.com.blog.api.enums.PostStatus;

import java.time.OffsetDateTime;
import java.util.List;

public record PostResponseDTO(

        Long id,
        String title,
        String content,
        PostStatus status,
        String authorUsername,
        List<String> categoryNames,
        Integer commentCount,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {}
