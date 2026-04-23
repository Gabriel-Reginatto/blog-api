package br.com.blog.api.core.domain.mapper;

import br.com.blog.api.api.dto.comment.request.CommentCreateRequestDTO;
import br.com.blog.api.api.dto.comment.response.CommentResponseDTO;
import br.com.blog.api.core.domain.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentResponseDTO toResponseDTO(Comment comment);

    Comment toEntity(CommentCreateRequestDTO dto);
}
