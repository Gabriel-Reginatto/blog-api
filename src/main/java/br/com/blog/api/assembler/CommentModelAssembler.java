package br.com.blog.api.assembler;

import br.com.blog.api.controller.CommentController;
import br.com.blog.api.dto.comment.response.CommentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommentModelAssembler implements RepresentationModelAssembler<CommentResponseDTO, EntityModel<CommentResponseDTO>> {

    @Override
    public EntityModel<CommentResponseDTO> toModel(CommentResponseDTO commentDTO) {
        return EntityModel.of(commentDTO,
                linkTo(methodOn(CommentController.class).findById(commentDTO.id())).withSelfRel().withType("GET"),
                linkTo(methodOn(CommentController.class).findCommentsByPostId(commentDTO.postId(), null)).withRel("postComments").withType("GET"),
                linkTo(methodOn(CommentController.class).deleteComment(commentDTO.id())).withRel("delete").withType("DELETE")
        );
    }
}