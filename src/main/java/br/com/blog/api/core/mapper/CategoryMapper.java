package br.com.blog.api.core.mapper;

import br.com.blog.api.api.dto.category.request.CategoryCreateRequestDTO;
import br.com.blog.api.api.dto.category.response.CategoryResponseDTO;
import br.com.blog.api.core.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toResponseDTO(Category category);

    Category toEntity(CategoryCreateRequestDTO dto);

    void updateEntity(CategoryCreateRequestDTO dto, @MappingTarget Category category);

}
