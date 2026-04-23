package br.com.blog.api.api.dto.comment.response;


import java.time.OffsetDateTime;

public record CommentResponseDTO(

        Long id,
        String content,
        String authorUsername,
        Long postId,
        OffsetDateTime createdAt
) {}
