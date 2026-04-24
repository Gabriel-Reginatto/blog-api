package br.com.blog.api.api.docs;

import br.com.blog.api.api.dto.comment.request.CommentCreateRequestDTO;
import br.com.blog.api.api.dto.comment.response.CommentResponseDTO;
import br.com.blog.api.infrastructure.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comments", description = "Endpoints for comment management")
public interface CommentControllerDoc {

    @Operation(summary = "Find comment by ID", description = "Returns a single comment")
    @ApiResponseGet
    ResponseEntity<EntityModel<CommentResponseDTO>> findById(@PathVariable Long id);

    @Operation(summary = "Create a comment", description = "Creates a new comment for a post")
    @ApiResponsePost
    ResponseEntity<EntityModel<CommentResponseDTO>> createComment(@PathVariable Long postId, @Valid @RequestBody CommentCreateRequestDTO request);

    @Operation(summary = "Find comments by post ID", description = "Returns paginated list of comments for a post")
    @ApiResponseGet
    ResponseEntity<PagedModel<EntityModel<CommentResponseDTO>>> findCommentsByPostId(@PathVariable Long postId, Pageable pageable);

    @Operation(summary = "Delete a comment", description = "Deletes a comment by ID")
    @ApiResponseDelete
    ResponseEntity<Void> deleteComment(@PathVariable Long id);
}