package br.com.blog.api.api.docs;

import br.com.blog.api.api.dto.post.request.PostCreateRequestDTO;
import br.com.blog.api.api.dto.post.request.PostUpdateRequestDTO;
import br.com.blog.api.api.dto.post.response.PostResponseDTO;
import br.com.blog.api.infrastructure.annotation.ApiResponseDelete;
import br.com.blog.api.infrastructure.annotation.ApiResponseGet;
import br.com.blog.api.infrastructure.annotation.ApiResponsePost;
import br.com.blog.api.infrastructure.annotation.ApiResponsePut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Posts", description = "Endpoints for posts management")
public interface PostControllerDoc {

    @Operation(
            summary = "Find post by ID",
            description = "Returns a single post with HATEOAS links"
    )
    @ApiResponseGet
    ResponseEntity<EntityModel<PostResponseDTO>> findById(@PathVariable Long id);

    @Operation(
            summary = "Find all posts",
            description = "Returns paginated list of all posts with HATEOAS links "
    )
    @ApiResponseGet
    ResponseEntity<PagedModel<EntityModel<PostResponseDTO>>> findAll(Pageable pageable);

    @Operation(
            summary = "Create a post",
            description = "Creates a new post and returns it with HATEOAS links"
    )
    @ApiResponsePost
    ResponseEntity<EntityModel<PostResponseDTO>> createPost(@Valid @RequestBody PostCreateRequestDTO request);

    @Operation(summary = "Update a post", description = "Updates an existing post")
    @ApiResponsePut
    ResponseEntity<EntityModel<PostResponseDTO>> updatePost(@PathVariable Long id, @Valid @RequestBody PostUpdateRequestDTO request);

    @Operation(summary = "Delete a post", description = "Deletes a post by ID")
    @ApiResponseDelete
    ResponseEntity<Void> deletePost(@PathVariable Long id);

}
