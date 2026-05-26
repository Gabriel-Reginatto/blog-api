package br.com.blog.api.infrastructure.annotation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static br.com.blog.api.infrastructure.annotation.ApiResponseConstants.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = CREATED, description = DESC_CREATED),
        @ApiResponse(responseCode = BAD_REQUEST, description = DESC_BAD_REQUEST),
        @ApiResponse(responseCode = CONFLICT, description = DESC_CONFLICT),
        @ApiResponse(responseCode = TOO_MANY_REQUESTS, description = TOO_MANY_REQUESTS),
        @ApiResponse(responseCode = INTERNAL_ERROR, description = DESC_INTERNAL_ERROR)
})
public @interface ApiResponsePost {
}
