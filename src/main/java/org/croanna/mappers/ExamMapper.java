package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.ExamDTO;
import org.croanna.models.Exam;

@ApplicationScoped
public class ExamMapper {
    @Inject
    DriverMapper driverMapper;

    @Inject
    InstructorMapper instructorMapper;

    public ExamDTO toDTO(Exam exam) {
        return new ExamDTO(
                exam.getId(),
                exam.getDate(),
                exam.getType(),
                exam.getDriver() != null ? driverMapper.toDTO(exam.getDriver()) : null,
                exam.getInstructor() != null ? instructorMapper.toDTO(exam.getInstructor()) : null
        );
    }
}
