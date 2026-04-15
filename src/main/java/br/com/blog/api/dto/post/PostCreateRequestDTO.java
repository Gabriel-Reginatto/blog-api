package br.com.blog.api.dto;

import br.com.blog.api.enums.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PostCreateRequestDTO(

        @NotBlank(message = "title is required")
        @Size(min = 5, max = 100, message = "title must be between 5 and 100 characters")
        String title,

        @NotBlank(message = "content is required")
        String content,

        @NotNull(message = "status is required")
        PostStatus status,

        @NotBlank(message = "authorUsername is required")
        String authorUsername,

        @NotNull(message = "categoryIds is required")
        @Size(min = 1, message = "post must have at least one category")
        List<Long> categoryIds

) {}