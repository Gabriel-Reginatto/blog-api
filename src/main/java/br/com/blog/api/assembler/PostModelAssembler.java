package br.com.blog.api.assembler;

import br.com.blog.api.controller.PostController;
import br.com.blog.api.dto.post.response.PostResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PostModelAssembler implements RepresentationModelAssembler<PostResponseDTO, EntityModel<PostResponseDTO>> {

    @Override
    public EntityModel<PostResponseDTO> toModel(PostResponseDTO post) {

        return EntityModel.of(post,
                linkTo(methodOn(PostController.class).findById(post.id())).withSelfRel().withType("GET"),
                linkTo(methodOn(PostController.class).findAll(null)).withRel("Find all").withType("GET"),
                linkTo(methodOn(PostController.class).createPost(null)).withRel("Create").withType("POST"),
                linkTo(methodOn(PostController.class).updatePost(post.id(), null)).withRel("Update").withType("PUT"),
                linkTo(methodOn(PostController.class)).withRel("Delete").withType("DELETE")
        );
    }
}
