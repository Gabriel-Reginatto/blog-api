package br.com.blog.api.controller;

import br.com.blog.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.dto.user.response.UserResponseDTO;
import br.com.blog.api.dto.user.request.UserUpdateRequestDTO;
import br.com.blog.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> findAll(Pageable pageable) {
        Page<UserResponseDTO> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserResponseDTO>> findById(@PathVariable Long id) {
        var user = userService.findById(id);

        EntityModel<UserResponseDTO> model = EntityModel.of(user);

        model.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        model.add(linkTo(methodOn(UserController.class).findAll(null)).withRel("allUsers"));
        model.add(linkTo(methodOn(UserController.class).updateUser(id, null)).withRel("update").withType("PUT"));
        model.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete").withType("DELETE"));

        return ResponseEntity.ok(model);
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserResponseDTO> findByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateRequestDTO request) {
        UserResponseDTO createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDTO request) {
        UserResponseDTO updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

