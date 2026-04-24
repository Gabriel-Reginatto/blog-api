package br.com.blog.api.api.controller;

import br.com.blog.api.api.assembler.PostModelAssembler;
import br.com.blog.api.api.docs.PostControllerDoc;
import br.com.blog.api.api.dto.post.request.PostCreateRequestDTO;
import br.com.blog.api.api.dto.post.request.PostUpdateRequestDTO;
import br.com.blog.api.api.dto.post.response.PostResponseDTO;
import br.com.blog.api.core.services.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController implements PostControllerDoc {

    private final PostService postService;
    private final PostModelAssembler postAssembler;
    private final PagedResourcesAssembler<PostResponseDTO> pagedAssembler;

    public PostController(PostService postService,
                          PostModelAssembler postAssembler,
                          PagedResourcesAssembler<PostResponseDTO> pagedAssembler) {
        this.postService = postService;
        this.postAssembler = postAssembler;
        this.pagedAssembler = pagedAssembler;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<EntityModel<PostResponseDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postAssembler.toModel(postService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<PostResponseDTO>>> findAll(Pageable pageable) {
        Page<PostResponseDTO> page = postService.findAll(pageable);
        PagedModel<EntityModel<PostResponseDTO>> pagedModel = pagedAssembler.toModel(page, postAssembler);

        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<PostResponseDTO>> createPost(@Valid @RequestBody PostCreateRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postAssembler.toModel(postService.createPost(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PostResponseDTO>> updatePost(@PathVariable Long id,
                                                                   @Valid @RequestBody PostUpdateRequestDTO request) {
        return ResponseEntity.ok(postAssembler.toModel(postService.updatePost(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}