package br.com.blog.api.controller;

import br.com.blog.api.assembler.UserModelAssembler;
import br.com.blog.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.dto.user.request.UserUpdateRequestDTO;
import br.com.blog.api.dto.user.response.UserResponseDTO;
import br.com.blog.api.services.UserService;
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
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserModelAssembler userAssembler;
    private final PagedResourcesAssembler<UserResponseDTO> pagedAssembler;

    public UserController(UserService userService, UserModelAssembler userAssembler, PagedResourcesAssembler<UserResponseDTO> pagedAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
        this.pagedAssembler = pagedAssembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UserResponseDTO>>> findAll(Pageable pageable) {
        Page<UserResponseDTO> page = userService.findAll(pageable);
        return ResponseEntity.ok(pagedAssembler.toModel(page, userAssembler));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserResponseDTO>> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(userAssembler.toModel(user));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<EntityModel<UserResponseDTO>> findByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userAssembler.toModel(userService.findByUsername(username)));
    }

    @PostMapping
    public ResponseEntity<EntityModel<UserResponseDTO>> createUser(@Valid @RequestBody UserCreateRequestDTO request) {
        UserResponseDTO createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAssembler.toModel(createdUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UserResponseDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDTO request) {
        UserResponseDTO updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(userAssembler.toModel(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

