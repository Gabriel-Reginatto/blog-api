package br.com.blog.api.mapper;

import br.com.blog.api.dto.post.request.PostCreateRequestDTO;
import br.com.blog.api.dto.post.response.PostResponseDTO;
import br.com.blog.api.dto.post.request.PostUpdateRequestDTO;
import br.com.blog.api.model.Category;
import br.com.blog.api.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "categoryNames", source = "categories")
    @Mapping(target = "commentCount", expression = "java(post.getComments().size())")
    PostResponseDTO toResponseDTO(Post post);

    default List<String> mapCategoryNames(Set<Category> categories) {
        if (categories == null) return null;
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    Post toEntity(PostCreateRequestDTO dto);

    void updateEntity(PostUpdateRequestDTO dto, @MappingTarget Post post);
}
