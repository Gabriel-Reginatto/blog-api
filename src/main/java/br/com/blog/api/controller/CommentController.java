package br.com.blog.api.controller;

import br.com.blog.api.dto.comment.CommentResponseDTO;
import br.com.blog.api.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long id) {
        CommentResponseDTO comment = commentService.findByID(id);
        return ResponseEntity.ok(comment);
    }




}
