package br.com.blog.api.api.dto.comment.request;

import jakarta.validation.constraints.NotBlank;

public record CommentCreateRequestDTO(

        @NotBlank(message = "content is required")
        String content,

        @NotBlank(message = "authorUsername is required")
        String authorUsername
) {}
