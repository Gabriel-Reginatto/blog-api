package br.com.blog.api.api.controller;

import br.com.blog.api.api.assembler.CategoryModelAssembler;
import br.com.blog.api.api.dto.category.request.CategoryCreateRequestDTO;
import br.com.blog.api.api.dto.category.response.CategoryResponseDTO;
import br.com.blog.api.core.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryModelAssembler categoryAssembler;
    private final PagedResourcesAssembler<CategoryResponseDTO> pagedResourcesAssembler;

    public CategoryController(CategoryService categoryService, CategoryModelAssembler categoryAssembler, PagedResourcesAssembler<CategoryResponseDTO> pagedResourcesAssembler) {
        this.categoryService = categoryService;
        this.categoryAssembler = categoryAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<CategoryResponseDTO>> createCategory(@Valid @RequestBody CategoryCreateRequestDTO request) {
        var created = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryAssembler.toModel(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CategoryResponseDTO>> findById(@PathVariable Long id) {
        var category = categoryService.findById(id);
        return ResponseEntity.ok(categoryAssembler.toModel(category));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<CategoryResponseDTO>>> findAll(Pageable pageable) {
        Page<CategoryResponseDTO> page = categoryService.findAll(pageable);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page, categoryAssembler));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CategoryResponseDTO>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryCreateRequestDTO request) {
        var updatedCategory = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(categoryAssembler.toModel(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
