package br.com.blog.api.mapper;

import br.com.blog.api.dto.comment.CommentCreateRequestDTO;
import br.com.blog.api.dto.comment.CommentResponseDTO;
import br.com.blog.api.entities.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentResponseDTO toResponseDTO(Comment comment);

    Comment toEntity(CommentCreateRequestDTO dto);
}
