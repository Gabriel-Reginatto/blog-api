package br.com.blog.api.services;

import br.com.blog.api.dto.category.request.CategoryCreateRequestDTO;
import br.com.blog.api.dto.category.response.CategoryResponseDTO;
import br.com.blog.api.model.Category;
import br.com.blog.api.exception.DuplicateResourceException;
import br.com.blog.api.exception.ResourceNotFoundException;
import br.com.blog.api.mapper.CategoryMapper;
import br.com.blog.api.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponseDTO createCategory(CategoryCreateRequestDTO request) {

        logger.info("Creating category with name: {}", request.name());

        if (categoryRepository.existsByName(request.name())) {
            throw new DuplicateResourceException("Comment", "name", request.name());
        }

        Category entity = categoryMapper.toEntity(request);
        entity.setCreatedAt(OffsetDateTime.now());

        var savedEntity = categoryRepository.save(entity);

        return categoryMapper.toResponseDTO(savedEntity);
    }

    public CategoryResponseDTO findById(Long id) {

        var category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category", id)
                );

        return categoryMapper.toResponseDTO(category);
    }

    public Page<CategoryResponseDTO> findAll(Pageable pageable) {

        logger.info("Finding all categories with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<Category> page = categoryRepository.findAll(pageable);

        return page.map(categoryMapper::toResponseDTO);
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryCreateRequestDTO request) {

        logger.info("Updating category with name: {}", request.name());

        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category", "name", request.name())
                );

        if (request.name() != null && !request.name().equals(category.getName())) {
            if (categoryRepository.existsByName(request.name())) {
                throw new DuplicateResourceException("Category", "name", request.name());
            }
        }

        categoryMapper.updateEntity(request, category);

        var savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDTO(savedCategory);
    }

    public void deleteCategory(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category", id)
                );

        categoryRepository.delete(category);
    }
}

