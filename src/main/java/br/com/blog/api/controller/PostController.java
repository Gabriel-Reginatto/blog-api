package br.com.blog.api.controller;

import br.com.blog.api.dto.post.PostCreateRequestDTO;
import br.com.blog.api.dto.post.PostResponseDTO;
import br.com.blog.api.dto.post.PostUpdateRequestDTO;
import br.com.blog.api.services.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id) {
        PostResponseDTO post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<Page<PostResponseDTO>> findAll(Pageable pageable) {
        Page<PostResponseDTO> posts = postService.findAll(pageable);

        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostCreateRequestDTO request) {
        var post = postService.createPost(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @Valid @RequestBody PostUpdateRequestDTO request) {
        var updatedPost = postService.updatePost(id, request);

        return ResponseEntity.ok().body(updatedPost);
    }



}
