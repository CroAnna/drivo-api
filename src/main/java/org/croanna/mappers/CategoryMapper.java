package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.dtos.CategoryDTO;
import org.croanna.models.Category;

@ApplicationScoped
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                category.getDescription(),
                category.getRequiredHours(),
                category.getMinimalAge()
        );
    }
}
