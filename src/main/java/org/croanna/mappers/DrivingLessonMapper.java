package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.CreateDrivingLessonDTO;
import org.croanna.dtos.DrivingLessonResponseDTO;
import org.croanna.models.DrivingLesson;
import org.croanna.repositories.DriverRepository;
import org.croanna.repositories.InstructorRepository;

@ApplicationScoped
public class DrivingLessonMapper {

    @Inject
    DriverRepository driverRepository;

    @Inject
    InstructorRepository instructorRepository;

    public DrivingLessonResponseDTO toResponseDTO(DrivingLesson lesson) {
        return new DrivingLessonResponseDTO(
                lesson.getId(),
                lesson.getStartTime(),
                lesson.getEndTime(),
                lesson.getLocation(),
                lesson.getComment(),
                lesson.getStatus(),
                lesson.getDriver() != null ? lesson.getDriver().getId() : null,
                lesson.getInstructor() != null ? lesson.getInstructor().getId() : null
        );
    }

    public DrivingLesson toModel(CreateDrivingLessonDTO lesson) {
        return new DrivingLesson(
                lesson.getId(),
                lesson.getStartTime(),
                lesson.getEndTime(),
                lesson.getLocation(),
                lesson.getComment(),
                lesson.getStatus(),
                lesson.getDriverId() != null ? driverRepository.findById(lesson.getDriverId()) : null,
                lesson.getInstructorId() != null ? instructorRepository.findById(lesson.getInstructorId()) : null
        );
    }

}
