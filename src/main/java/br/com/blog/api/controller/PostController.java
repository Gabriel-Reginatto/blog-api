package br.com.blog.api.controller;

import br.com.blog.api.assembler.PostModelAssembler;
import br.com.blog.api.dto.post.request.PostCreateRequestDTO;
import br.com.blog.api.dto.post.response.PostResponseDTO;
import br.com.blog.api.dto.post.request.PostUpdateRequestDTO;
import br.com.blog.api.services.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostModelAssembler postAssembler;
    private final PagedResourcesAssembler<PostResponseDTO> pagedAssembler;

    public PostController(PostService postService, PostModelAssembler postAssembler, PagedResourcesAssembler<PostResponseDTO> pagedAssembler) {
        this.postService = postService;
        this.postAssembler = postAssembler;
        this.pagedAssembler = pagedAssembler;

    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PostResponseDTO>> findById(@PathVariable Long id) {
        PostResponseDTO post = postService.findById(id);
        return ResponseEntity.ok(postAssembler.toModel(post));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<PostResponseDTO>>> findAll(Pageable pageable) {
        Page<PostResponseDTO> posts = postService.findAll(pageable);

    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostCreateRequestDTO request) {
        var post = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @Valid @RequestBody PostUpdateRequestDTO request) {
        var updatedPost = postService.updatePost(id, request);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}