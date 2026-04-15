package br.com.blog.api.dto.post;

import br.com.blog.api.enums.PostStatus;

import java.util.List;

public record PostUpdateRequestDTO(

        String title,
        String content,
        PostStatus status,
        List<Long> categoryIds
)
{}
