package br.com.blog.api.services;

import br.com.blog.api.dto.comment.CommentCreateRequestDTO;
import br.com.blog.api.dto.comment.CommentResponseDTO;
import br.com.blog.api.entities.Comment;
import br.com.blog.api.entities.Post;
import br.com.blog.api.entities.User;
import br.com.blog.api.exception.ResourceNotFoundException;
import br.com.blog.api.mapper.CommentMapper;
import br.com.blog.api.repositories.CommentRepository;
import br.com.blog.api.repositories.PostRepository;
import br.com.blog.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public CommentResponseDTO findByID(Long id) {

        logger.info("Finding comment by ID: {}" ,id);

        var comment = commentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Comment", id)
                );

        return commentMapper.toResponseDTO(comment);
    }

    public CommentResponseDTO createComment(Long postId, CommentCreateRequestDTO request) {

        logger.info("Creating comment for post ID: {} by author {}", postId, request.authorUsername());

        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Post", postId)
                );

        User user = userRepository.findByUsername(request.authorUsername())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "username", request.authorUsername())
                );

        var comment = commentMapper.toEntity(request);
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setCreatedAt(OffsetDateTime.now());

        var savedComment = commentRepository.save(comment);

        return commentMapper.toResponseDTO(savedComment);
    }
}
