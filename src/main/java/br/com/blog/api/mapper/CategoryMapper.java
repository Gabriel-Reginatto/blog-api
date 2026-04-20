package br.com.blog.api.mapper;

import br.com.blog.api.dto.category.CategoryCreateRequestDTO;
import br.com.blog.api.dto.category.CategoryResponseDTO;
import br.com.blog.api.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toResponseDTO(Category category);

    Category toEntity(CategoryCreateRequestDTO dto);

    void updateEntity(CategoryCreateRequestDTO dto, @MappingTarget Category category);

}
