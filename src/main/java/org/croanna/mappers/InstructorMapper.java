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

@ApplicationScoped
public class InstructorMapper {

    @Inject
    InstructorMapper instructorMapper;

    @Inject
    CategoryService categoryService;

    public InstructorResponseDTO toResponseDTO(Instructor instructor) {
        return new InstructorResponseDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getPhone(),
                instructor.getAvailability()
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
