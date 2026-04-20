package br.com.blog.api.mapper;

import br.com.blog.api.dto.comment.request.CommentCreateRequestDTO;
import br.com.blog.api.dto.comment.response.CommentResponseDTO;
import br.com.blog.api.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentResponseDTO toResponseDTO(Comment comment);

    Comment toEntity(CommentCreateRequestDTO dto);
}
