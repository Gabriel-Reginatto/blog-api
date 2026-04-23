package br.com.blog.api.api.assembler;

import br.com.blog.api.api.controller.CategoryController;
import br.com.blog.api.api.dto.category.response.CategoryResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<CategoryResponseDTO, EntityModel<CategoryResponseDTO>> {

    @Override
    public EntityModel<CategoryResponseDTO> toModel(CategoryResponseDTO category) {

        return EntityModel.of(category,
                linkTo(methodOn(CategoryController.class).findById(category.id())).withSelfRel().withType("GET"),
                linkTo(methodOn(CategoryController.class).findAll(null)).withRel("allCategories").withType("GET"),
                linkTo(methodOn(CategoryController.class).createCategory(null)).withRel("create").withType("POST"),
                linkTo(methodOn(CategoryController.class).updateCategory(category.id(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(CategoryController.class).deleteCategory(category.id())).withRel("delete").withType("DELETE")
        );
    }
}