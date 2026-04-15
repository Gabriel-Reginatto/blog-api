package br.com.blog.api.dto.comment;

import jakarta.validation.constraints.NotBlank;

public record CommentCreateRequestDTO(

        @NotBlank(message = "content is required")
        String content,

        @NotBlank(message = "authorUsername is required")
        String authorUsername
) {}
