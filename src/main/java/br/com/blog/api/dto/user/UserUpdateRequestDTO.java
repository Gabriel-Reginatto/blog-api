package br.com.blog.api.dto.user;

public record UserUpdateRequestDTO(
        String username,
        String email,
        String bio
) {
}
