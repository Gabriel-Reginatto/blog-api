package br.com.blog.api.controller;

import br.com.blog.api.dto.comment.CommentCreateRequestDTO;
import br.com.blog.api.dto.comment.CommentResponseDTO;
import br.com.blog.api.services.CommentService;
import jakarta.validation.Valid;
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

    @GetMapping("{id}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long id) {
       var comment = commentService.findByID(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long postId, @Valid @RequestBody CommentCreateRequestDTO request) {
        var post = commentService.createComment(postId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }




}
