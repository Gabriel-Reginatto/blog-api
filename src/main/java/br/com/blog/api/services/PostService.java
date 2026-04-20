package br.com.blog.api.services;

import br.com.blog.api.dto.post.PostCreateRequestDTO;
import br.com.blog.api.dto.post.PostResponseDTO;
import br.com.blog.api.dto.post.PostUpdateRequestDTO;
import br.com.blog.api.model.Category;
import br.com.blog.api.model.Post;
import br.com.blog.api.exception.ResourceNotFoundException;
import br.com.blog.api.mapper.PostMapper;
import br.com.blog.api.repositories.CategoryRepository;
import br.com.blog.api.repositories.PostRepository;
import br.com.blog.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostMapper postMapper;
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.postMapper = postMapper;
    }

    public PostResponseDTO createPost(PostCreateRequestDTO request) {

        logger.info("Creating post with author: {}", request.authorUsername());

        var user = userRepository.findByUsername(request.authorUsername())
                .orElseThrow(
                        () -> new ResourceNotFoundException("authorUsername")
                );

        var categories = categoryRepository.findAllById(request.categoryIds());

        if (categories.size() != request.categoryIds().size()) {
            Set<Long> foundIds = categories.stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());

            List<Long> notFoundIds = request.categoryIds().stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();

            throw new ResourceNotFoundException("Categories not found with IDs: " + notFoundIds);
        }

        Set<Category> cat = new HashSet<>(categories);

        Post post = postMapper.toEntity(request);
        post.setAuthor(user);
        post.setCategories(cat);
        post.setCreatedAt(OffsetDateTime.now());

        Post savedPost = postRepository.save(post);

        return postMapper.toResponseDTO(savedPost);
    }

    public PostResponseDTO findById(Long id) {

        logger.info("Finding post with ID: {}", id);

        Post post = postRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Post", id)
                );

        return postMapper.toResponseDTO(post);
    }

    public Page<PostResponseDTO> findAll(Pageable pageable) {

        logger.info("Findind all posts with pagination: page {}, size {}",
                pageable.getPageNumber(), pageable.getPageSize());

        Page<Post> page = postRepository.findAll(pageable);

        return page.map(postMapper::toResponseDTO);
    }

    public PostResponseDTO updatePost(Long id, PostUpdateRequestDTO request){

        logger.info("Updating post with id {}", id);

        Post post = postRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Post", id)
                );

        postMapper.updateEntity(request, post);

        if (request.categoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(request
                    .categoryIds());

            if (categories.size() != request.categoryIds().size()) {
                throw new ResourceNotFoundException("One or more category Ids not found");
            }

            post.setCategories(new HashSet<>(categories));
        }

        post.setUpdatedAt(OffsetDateTime.now());

        var savedPost = postRepository.save(post);

        return postMapper.toResponseDTO(savedPost);
    }

    public void delete(Long id) {

        logger.info("Deleting post with ID: {}", id);

        var post = postRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Post", id)
                );

        postRepository.delete(post);
    }
}
