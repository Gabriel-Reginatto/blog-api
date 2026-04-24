package br.com.blog.api.api.dto.comment.response;


import org.springframework.hateoas.server.core.Relation;

import java.time.OffsetDateTime;

@Relation(collectionRelation = "Comments", itemRelation = "comment")
public record CommentResponseDTO(

        Long id,
        String content,
        String authorUsername,
        Long postId,
        OffsetDateTime createdAt
) {}
