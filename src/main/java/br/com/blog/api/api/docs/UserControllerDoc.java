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

    ResponseEntity<PagedModel<EntityModel<UserResponseDTO>>> findAll(Pageable pageable);

    ResponseEntity<EntityModel<UserResponseDTO>> findById(@PathVariable Long id);

    ResponseEntity<EntityModel<UserResponseDTO>> findByUsername(@PathVariable String username);

    ResponseEntity<EntityModel<UserResponseDTO>> createUser(@Valid @RequestBody UserCreateRequestDTO request);

    ResponseEntity<EntityModel<UserResponseDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDTO request);

    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
