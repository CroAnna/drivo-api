package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.CategoryResponseDTO;
import org.croanna.dtos.CreateCategoryDTO;
import org.croanna.dtos.UpdateCategoryDTO;
import org.croanna.mappers.CategoryMapper;
import org.croanna.models.Category;
import org.croanna.repositories.CategoryRepository;

import java.util.List;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository repository;

    @Inject
    CategoryMapper mapper;

    public List<CategoryResponseDTO> getAllCategories(int page, int size, Long instructorId) {
        return repository.findAll(page, size, instructorId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = repository.findById(id);
        return mapper.toResponseDTO(category);
    }

    public Long getTotal() {
        return repository.count();
    }

    public List<Category> getAllCategoriesByInstructor(Long instructorId) {
        return repository.findAll(1, 1000, instructorId).stream().toList();
    }

    @Transactional
    public CategoryResponseDTO createCategory(CreateCategoryDTO dto) {
        Category category = mapper.toModel(dto);
        category = repository.save(category);
        return mapper.toResponseDTO(category);
    }

    @Transactional
    public CategoryResponseDTO updateCategory(Long id, UpdateCategoryDTO dto) {
        Category category = repository.findById(id);

        if (dto.getTitle() != null) {
            category.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            category.setDescription(dto.getDescription());
        }
        if (dto.getMinimalAge() != null) {
            category.setMinimalAge(dto.getMinimalAge());
        }
        if (dto.getRequiredHours() != null) {
            category.setRequiredHours(dto.getRequiredHours());
        }

        category = repository.update(category);
        return mapper.toResponseDTO(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        repository.delete(id);
    }
}
