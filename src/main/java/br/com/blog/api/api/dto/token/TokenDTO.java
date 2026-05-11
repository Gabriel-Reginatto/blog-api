package br.com.blog.api.api.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenDTO(

        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("refresh_token")
        String refreshToken,

        @JsonProperty("token_type")
        String tokenType,

        @JsonProperty("expires_in")
        Long expiresIn
) {
}
