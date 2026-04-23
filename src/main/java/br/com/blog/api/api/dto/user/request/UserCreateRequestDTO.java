package br.com.blog.api.api.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDTO(

        @NotBlank(message = "username is required")
        @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
        String username,

        @NotBlank(message = "email is required")
        @Email(message = "email must be valid")
        String email,

        @NotBlank(message = "first_name is required")
        @Size(min = 2, max = 100, message = "firstName must be between 3 and 180 characters")
        String firstName,

        @NotBlank(message = "last_name is required")
        @Size(min = 3, max = 100, message = "last_name must be between 3 and 180 characters")
        String lastName,

        String bio
){}
