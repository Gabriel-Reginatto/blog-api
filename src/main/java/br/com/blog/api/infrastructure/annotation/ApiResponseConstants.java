package br.com.blog.api.infrastructure.annotation;

public final class ApiResponseConstants {

    private ApiResponseConstants() {}

    public static final String OK = "200";
    public static final String CREATED = "201";
    public static final String NO_CONTENT = "204";
    public static final String BAD_REQUEST = "400";
    public static final String NOT_FOUND = "404";
    public static final String CONFLICT = "409";
    public static final String INTERNAL_ERROR = "500";

    public static final String DESC_OK = "Success";
    public static final String DESC_CREATED = "Resource created successfully";
    public static final String DESC_NO_CONTENT = "Resource deleted successfully";
    public static final String DESC_BAD_REQUEST = "Bad request or validation error";
    public static final String DESC_NOT_FOUND = "Resource not found";
    public static final String DESC_CONFLICT = "Resource already exists";
    public static final String DESC_INTERNAL_ERROR = "Internal server error";

}
