package br.com.blog.api.controller;

import br.com.blog.api.dto.comment.CommentCreateRequestDTO;
import br.com.blog.api.dto.comment.CommentResponseDTO;
import br.com.blog.api.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long id) {
        var comment = commentService.findByID(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long postId, @Valid @RequestBody CommentCreateRequestDTO request) {
        var comment = commentService.createComment(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<CommentResponseDTO>> findCommentsByPostId(@PathVariable Long postId, Pageable pageable) {
        Page<CommentResponseDTO> comments = commentService.findCommentByPostId(postId, pageable);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return ResponseEntity.noContent().build();
    }
}