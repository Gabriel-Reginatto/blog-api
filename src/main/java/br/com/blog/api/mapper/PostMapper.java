package br.com.blog.api.mapper;

import br.com.blog.api.dto.post.PostCreateRequestDTO;
import br.com.blog.api.dto.post.PostResponseDTO;
import br.com.blog.api.dto.post.PostUpdateRequestDTO;
import br.com.blog.api.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostResponseDTO toResponseDTO(Post post);

    Post toEntity(PostCreateRequestDTO dto);

    void updateEntity(PostUpdateRequestDTO dto, @MappingTarget Post post);
}
