package br.com.blog.api.api.docs;

import br.com.blog.api.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.api.dto.user.request.UserUpdateRequestDTO;
import br.com.blog.api.api.dto.user.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Users", description = "Endpoints for user management")
public interface UserControllerDoc {

    @Operation(
            summary = "Find all users",
            description = "Returns paginated list of all users with HATEOAS links",
            tags = {"Users"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    ResponseEntity<PagedModel<EntityModel<UserResponseDTO>>> findAll(Pageable pageable);

    @Operation(
            summary = "Find user by their ID",
            description = "Returns a user by ID",
            tags = {"Users"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content)
            }

    )
    ResponseEntity<EntityModel<UserResponseDTO>> findById(@PathVariable Long id);

    @SuppressWarnings("unused")
    @Operation(
            summary = "Find user by your username",
            description = "Returns a user by username",
            tags = {"Users"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            }
    )
    ResponseEntity<EntityModel<UserResponseDTO>> findByUsername(@PathVariable String username);

    @Operation(
            summary = "Create a user",
            description = "Returns the created user",
            tags = {"Users"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content),
            }
    )
    ResponseEntity<EntityModel<UserResponseDTO>> createUser(@Valid @RequestBody UserCreateRequestDTO request);

    @Operation(
            summary = "Updates a user",
            description = "Return a updated user",
            tags = {"Users"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            }
    )
    ResponseEntity<EntityModel<UserResponseDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDTO request);

    @Operation(
            summary = "Deletes a user by ID",
            description = "Deletes a user by ID (returns no content)",
            tags = {"Users"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            }
    )
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
