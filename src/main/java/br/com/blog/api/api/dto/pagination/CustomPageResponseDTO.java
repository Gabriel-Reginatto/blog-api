package br.com.blog.api.api.dto.pagination;

import java.util.List;

public record CustomPageResponseDTO<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
