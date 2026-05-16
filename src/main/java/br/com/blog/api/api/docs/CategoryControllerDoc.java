package br.com.blog.api.api.docs;

import br.com.blog.api.api.dto.category.request.CategoryCreateRequestDTO;
import br.com.blog.api.api.dto.category.response.CategoryResponseDTO;
import br.com.blog.api.infrastructure.annotation.ApiResponseDelete;
import br.com.blog.api.infrastructure.annotation.ApiResponseGet;
import br.com.blog.api.infrastructure.annotation.ApiResponsePost;
import br.com.blog.api.infrastructure.annotation.ApiResponsePut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Categories", description = "Endpoints for categories management")
public interface CategoryControllerDoc {

    @Operation(
            summary = "Create a category",
            description = "Creates a new category and returns it with HATEOAS links"
    )
    @ApiResponsePost
    ResponseEntity<EntityModel<CategoryResponseDTO>> createCategory(@Valid @RequestBody CategoryCreateRequestDTO request);

    @Operation(
            summary = "Find category by ID",
            description = "Return a single category with HATEOAS links"
    )
    @ApiResponseGet
    ResponseEntity<EntityModel<CategoryResponseDTO>> findById(@PathVariable Long id);

    @Operation(
            summary = "Find all categories",
            description = "Returns paginated list of all categories- with HATEOAS links"
    )
    @ApiResponseGet
    ResponseEntity<PagedModel<EntityModel<CategoryResponseDTO>>> findAll(Pageable pageable);

    @Operation(
            summary = "Update a category",
            description = "Updating an existing category and returns it with HATEOAS links"
    )
    @ApiResponsePut
    ResponseEntity<EntityModel<CategoryResponseDTO>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryCreateRequestDTO request);

    @Operation(
            summary = "Delete category by ID",
            description = "Deletes a category by ID (returns no content)"
    )
    @ApiResponseDelete
    ResponseEntity<Void> deleteCategory(@PathVariable Long id);


}
