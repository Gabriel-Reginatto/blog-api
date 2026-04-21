package br.com.blog.api.assembler;

import br.com.blog.api.controller.UserController;
import br.com.blog.api.dto.user.response.UserResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserResponseDTO, EntityModel<UserResponseDTO>> {

    @Override
    public EntityModel<UserResponseDTO> toModel(UserResponseDTO user) {

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).findById(user.id())).withSelfRel(),
                linkTo(methodOn(UserController.class).findAll(null)).withRel("allUsers"),
                linkTo(methodOn(UserController.class).updateUser(user.id(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(UserController.class).deleteUser(user.id())).withRel("delete").withType("DELETE"),
                linkTo(methodOn(UserController.class).createUser(null)).withRel("create").withType("POST")
        );
    }
}