package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.CategoryDTO;
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

    public List<CategoryDTO> getAllCategories(int page, int size, Long instructorId) {
        return repository.findAll(page, size, instructorId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public Long getTotal() {
        return repository.count();
    }

    public List<Category> getAllCategoriesByInstructor(Long instructorId) {
        return repository.findAll(1, 1000, instructorId).stream().toList();
    }
}
