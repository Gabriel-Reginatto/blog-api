package br.com.blog.api.api.controller;

import br.com.blog.api.api.assembler.CommentModelAssembler;
import br.com.blog.api.api.docs.CommentControllerDoc;
import br.com.blog.api.api.dto.comment.request.CommentCreateRequestDTO;
import br.com.blog.api.api.dto.comment.response.CommentResponseDTO;
import br.com.blog.api.core.services.CommentService;
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
@RequestMapping("/api/v1/comments")
public class CommentController implements CommentControllerDoc {

    private final CommentService commentService;
    private final CommentModelAssembler commentAssembler;
    private final PagedResourcesAssembler<CommentResponseDTO> pagedResourcesAssembler;

    public CommentController(CommentService commentService, CommentModelAssembler commentAssembler, PagedResourcesAssembler<CommentResponseDTO> pagedResourcesAssembler) {
        this.commentService = commentService;
        this.commentAssembler = commentAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;

    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CommentResponseDTO>> findById(@PathVariable Long id) {
        var comment = commentService.findByID(id);
        return ResponseEntity.ok(commentAssembler.toModel(comment));
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<EntityModel<CommentResponseDTO>> createComment(@PathVariable Long postId, @Valid @RequestBody CommentCreateRequestDTO request) {
        var comment = commentService.createComment(postId, request);
        EntityModel<CommentResponseDTO> commentResponse = commentAssembler.toModel(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PagedModel<EntityModel<CommentResponseDTO>>> findCommentsByPostId(@PathVariable Long postId, Pageable pageable) {
        Page<CommentResponseDTO> page = commentService.findCommentByPostId(postId, pageable);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page, commentAssembler));
        // Pode colocar isso em qualquer lugar temporariamente, ex: no método main ou num Controller
        String url = System.getenv("DB_URL");
        System.out.println("DB_URL via System.getenv: " + url);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}