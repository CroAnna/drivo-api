package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.ExamDTO;
import org.croanna.models.Exam;
import org.croanna.repositories.DriverRepository;
import org.croanna.repositories.InstructorRepository;

@ApplicationScoped
public class ExamMapper {

    @Inject
    DriverRepository driverRepository;

    @Inject
    InstructorRepository instructorRepository;

    public ExamDTO toDTO(Exam exam) {
        return new ExamDTO(
                exam.getId(),
                exam.getDate(),
                exam.getStatus(),
                exam.getType(),
                exam.getDriver() != null ? exam.getDriver().getId() : null,
                exam.getInstructor() != null ? exam.getInstructor().getId() : null
        );
    }

    public Exam toModel(ExamDTO exam) {
        return new Exam(
                exam.getId(),
                exam.getDate(),
                exam.getStatus(),
                exam.getType(),
                driverRepository.findById(exam.getDriverId()),
                instructorRepository.findById(exam.getInstructorId())
        );
    }
}
