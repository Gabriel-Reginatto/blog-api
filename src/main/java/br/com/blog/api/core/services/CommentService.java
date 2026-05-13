package br.com.blog.api.core.services;

import br.com.blog.api.api.dto.comment.request.CommentCreateRequestDTO;
import br.com.blog.api.api.dto.comment.response.CommentResponseDTO;
import br.com.blog.api.core.domain.Comment;
import br.com.blog.api.core.domain.Post;
import br.com.blog.api.core.domain.User;
import br.com.blog.api.infrastructure.exception.ResourceNotFoundException;
import br.com.blog.api.core.mapper.CommentMapper;
import br.com.blog.api.core.repositories.CommentRepository;
import br.com.blog.api.core.repositories.PostRepository;
import br.com.blog.api.core.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        var savedComment = commentRepository.save(comment);

        return commentMapper.toResponseDTO(savedComment);
    }

    public Page<CommentResponseDTO> findCommentByPostId(Long postId, Pageable pageable) {

        logger.info("Finding post with ID: {}", postId);

        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post", postId);
        }

        Page<Comment> comments = commentRepository.findByPostId(postId, pageable);

        return comments.map(commentMapper::toResponseDTO);
    }

    public void deleteComment(Long id) {

        logger.info("Deleting comment with ID: {}", id);

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", id));

        commentRepository.delete(comment);
    }

    public boolean isAuthor(Long commentId, String username) {
        return commentRepository.findById(commentId)
                .map(comment -> comment.getAuthor().getUsername().equalsIgnoreCase(username))
                .orElse(false);
    }
}
