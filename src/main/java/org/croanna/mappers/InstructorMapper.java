package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.InstructorDTO;
import org.croanna.models.Instructor;

@ApplicationScoped
public class InstructorMapper {

    @Inject
    InstructorMapper instructorMapper;

    public InstructorDTO toDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getPhone(),
                instructor.getAvailability()
        );
    }
}
