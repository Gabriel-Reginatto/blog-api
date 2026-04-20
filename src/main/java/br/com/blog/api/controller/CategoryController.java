package br.com.blog.api.controller;

import br.com.blog.api.dto.category.request.CategoryCreateRequestDTO;
import br.com.blog.api.dto.category.response.CategoryResponseDTO;
import br.com.blog.api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryCreateRequestDTO request) {
        var created = categoryService.createCategory(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
