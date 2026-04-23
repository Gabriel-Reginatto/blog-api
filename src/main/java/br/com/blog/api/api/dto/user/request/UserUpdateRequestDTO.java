package br.com.blog.api.api.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO(

        @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
        String username,

        @Email(message = "email must be valid")
        String email,

        @Size(min = 2, max = 50, message = "first name must be between 2 and 50 characters")
        String firstName,

        @Size(min = 2, max = 100, message = "last name must be between 2 and 100 characters")
        String lastName,

        @Size(max = 500, message = "bio max 500 characters")
        String bio

) {}