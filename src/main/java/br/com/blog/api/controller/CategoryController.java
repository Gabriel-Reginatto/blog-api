package br.com.blog.api.controller;

import br.com.blog.api.dto.category.request.CategoryCreateRequestDTO;
import br.com.blog.api.dto.category.response.CategoryResponseDTO;
import br.com.blog.api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
        var category = categoryService.findById(id);

        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDTO>> findAll(Pageable pageable) {
        var allCategories = categoryService.findAll(pageable);

        return ResponseEntity.ok(allCategories);
    }

    @PutMapping("/{íd}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryCreateRequestDTO request) {
        var updatedCategory = categoryService.updateCategory(id, request);

        return ResponseEntity.ok().body(updatedCategory);
    }

}
