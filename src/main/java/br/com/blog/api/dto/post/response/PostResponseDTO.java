package br.com.blog.api.dto.post.response;

import br.com.blog.api.enums.PostStatus;
import org.springframework.hateoas.server.core.Relation;

import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "Posts")
public record PostResponseDTO(

        Long id,
        String title,
        String content,
        PostStatus status,
        String authorUsername,
        List<String> categoryNames,
        Integer commentCount,
        OffsetDateTime createdAt
) {}
