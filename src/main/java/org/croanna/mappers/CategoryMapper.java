package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.CategoryResponseDTO;
import org.croanna.dtos.CreateCategoryDTO;
import org.croanna.models.Category;
import org.croanna.models.Instructor;
import org.croanna.services.InstructorService;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class CategoryMapper {

    @Inject
    InstructorService instructorService;

    public CategoryResponseDTO toResponseDTO(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getTitle(),
                category.getDescription(),
                category.getRequiredHours(),
                category.getMinimalAge()
        );
    }

    public Category toModel(CreateCategoryDTO category) {
        Long categoryId = category.getId();
        Set<Instructor> instructors = new HashSet<>(instructorService.getAllInstructorsByCategory(categoryId));

        return new Category(
                category.getId(),
                category.getTitle(),
                category.getDescription(),
                category.getRequiredHours(),
                category.getMinimalAge(),
                instructors
        );
    }
}
