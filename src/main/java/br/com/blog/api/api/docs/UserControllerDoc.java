package br.com.blog.api.api.docs;

import br.com.blog.api.api.dto.pagination.CustomPageResponseDTO;
import br.com.blog.api.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.api.dto.user.request.UserUpdateRequestDTO;
import br.com.blog.api.api.dto.user.response.UserResponseDTO;
import br.com.blog.api.infrastructure.annotation.ApiResponseDelete;
import br.com.blog.api.infrastructure.annotation.ApiResponseGet;
import br.com.blog.api.infrastructure.annotation.ApiResponsePost;
import br.com.blog.api.infrastructure.annotation.ApiResponsePut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Users", description = "Endpoints for user management")
public interface UserControllerDoc {

    @Operation(
            summary = "Find all users",
            description = "Returns paginated list of all users with HATEOAS links"
    )
    @ApiResponseGet
    ResponseEntity<CustomPageResponseDTO<UserResponseDTO>> findAll(
            @RequestParam(required = false) String username,
            Pageable pageable);

    @Operation(
            summary = "Find user by ID",
            description = "Returns a single user with HATEOAS links"
    )
    @ApiResponseGet
    ResponseEntity<EntityModel<UserResponseDTO>> findById(@PathVariable Long id);

    @Operation(
            summary = "Find user by username",
            description = "Return a single user by their username"
    )
    @ApiResponseGet
    ResponseEntity<EntityModel<UserResponseDTO>> findByUsername(@PathVariable String username);

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user and returns it with HATEOAS links"
    )
    @ApiResponsePost
    ResponseEntity<EntityModel<UserResponseDTO>> create(@Valid @RequestBody UserCreateRequestDTO request);

    @Operation(
            summary = "Update a user",
            description = "Updates an existing user and returns it with HATEOAS links"
    )
    @ApiResponsePut
    ResponseEntity<EntityModel<UserResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDTO request);

    @Operation(
            summary = "Delete a user",
            description = "Deletes a user by ID (returns no content)"
    )
    @ApiResponseDelete
    ResponseEntity<Void> delete(@PathVariable Long id);
}
