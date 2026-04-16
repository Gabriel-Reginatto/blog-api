package br.com.blog.api.exception;

import java.time.OffsetDateTime;

public record ErroResponse(
        int status,
        String error,
        String message,
        OffsetDateTime timestamp
) {}
