package br.com.blog.api.api.dto.post.request;

import br.com.blog.api.core.enums.PostStatus;

import java.util.List;

public record PostUpdateRequestDTO(

        String title,
        String content,
        PostStatus status,
        List<Long> categoryIds
)
{}
