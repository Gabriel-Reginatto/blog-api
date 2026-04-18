package br.com.blog.api.services;

import br.com.blog.api.dto.comment.CommentResponseDTO;
import br.com.blog.api.exception.ResourceNotFoundException;
import br.com.blog.api.mapper.CommentMapper;
import br.com.blog.api.repositories.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public CommentResponseDTO findByID(Long id) {

        logger.info("Finding comment by ID: {}" ,id);

        var comment = commentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Comment", id)
                );

        return commentMapper.toResponseDTO(comment);
    }
}
