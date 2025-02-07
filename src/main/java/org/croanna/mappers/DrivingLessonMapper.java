package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.DrivingLessonDTO;
import org.croanna.models.DrivingLesson;

@ApplicationScoped
public class DrivingLessonMapper {

    @Inject
    DriverMapper driverMapper;

    public DrivingLessonDTO toDTO(DrivingLesson lesson) {
        return new DrivingLessonDTO(
                lesson.getId(),
                lesson.getStartTime(),
                lesson.getEndTime(),
                lesson.getLocation(),
                lesson.getComment(),
                lesson.getStatus(),
                lesson.getDriver() != null ? driverMapper.toDTO(lesson.getDriver()) : null
        );
    }
}
