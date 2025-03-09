package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.CreateInstructorDTO;
import org.croanna.dtos.InstructorResponseDTO;
import org.croanna.models.Category;
import org.croanna.models.Instructor;
import org.croanna.services.CategoryService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class InstructorMapper {

    @Inject
    CategoryService categoryService;

    public InstructorResponseDTO toResponseDTO(Instructor instructor) {
        return new InstructorResponseDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getPhone(),
                instructor.getUsername(),
                instructor.getRole(),
                instructor.getAvailability(),
                instructor.getCategories().stream()
                        .map(category -> category.getTitle())
                        .collect(Collectors.toSet())
        );
    }

    public Instructor toModel(CreateInstructorDTO instructor) {
        Long instructorId = instructor.getId();
        Set<Category> categories = new HashSet<>(categoryService.getAllCategoriesByInstructor(instructorId));

        return new Instructor(
                instructor.getId(),
                instructor.getUsername(),
                instructor.getPassword(),
                instructor.getName(),
                instructor.getPhone(),
                instructor.getAvailability(),
                categories
        );
    }
}
