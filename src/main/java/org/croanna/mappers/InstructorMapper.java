package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.InstructorDTO;
import org.croanna.models.Category;
import org.croanna.models.Instructor;
import org.croanna.services.CategoryService;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class InstructorMapper {

    @Inject
    InstructorMapper instructorMapper;

    @Inject
    CategoryService categoryService;

    public InstructorDTO toDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getPhone(),
                instructor.getAvailability()
        );
    }

    public Instructor toModel(InstructorDTO instructor) {
        Long instructorId = instructor.getId();
        Set<Category> categories = new HashSet<>(categoryService.getAllCategoriesByInstructor(instructorId));

        return new Instructor(
                instructor.getId(),
                instructor.getName(),
                instructor.getPhone(),
                instructor.getAvailability(),
                categories
        );
    }
}
