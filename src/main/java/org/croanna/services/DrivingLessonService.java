package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.CreateDrivingLessonDTO;
import org.croanna.dtos.DrivingLessonResponseDTO;
import org.croanna.dtos.UpdateDrivingLessonDTO;
import org.croanna.mappers.DrivingLessonMapper;
import org.croanna.models.DrivingLesson;
import org.croanna.repositories.DriverRepository;
import org.croanna.repositories.DrivingLessonRepository;
import org.croanna.repositories.EmployeeRepository;

import java.util.List;

@ApplicationScoped
public class DrivingLessonService {

    @Inject
    DrivingLessonRepository repository;

    @Inject
    DriverRepository driverRepository;

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    DrivingLessonMapper mapper;

    public List<DrivingLessonResponseDTO> getAllDrivingLessons(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public DrivingLessonResponseDTO getDrivingLessonById(Long id) {
        DrivingLesson lesson = repository.findById(id);
        return mapper.toResponseDTO(lesson);
    }

    public Long getTotal() {
        return repository.count();
    }

    @Transactional
    public DrivingLessonResponseDTO createDrivingLesson(CreateDrivingLessonDTO dto) {
        DrivingLesson lesson = mapper.toModel(dto);
        lesson = repository.save(lesson);
        return mapper.toResponseDTO(lesson);
    }

    @Transactional
    public DrivingLessonResponseDTO updateDrivingLesson(Long id, UpdateDrivingLessonDTO dto) {
        DrivingLesson lesson = repository.findById(id);

        if (dto.getComment() != null) {
            lesson.setComment(dto.getComment());
        }
        if (dto.getLocation() != null) {
            lesson.setComment(dto.getLocation());
        }
        if (dto.getStatus() != null) {
            lesson.setStatus(dto.getStatus());
        }
        if (dto.getStartTime() != null) {
            lesson.setStartTime(dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            lesson.setEndTime(dto.getEndTime());
        }
        if (dto.getDriverId() != null) {
            lesson.setDriver(driverRepository.findById(dto.getDriverId()));
        }
        if (dto.getEmployeeId() != null) {
            lesson.setEmployee(employeeRepository.findById(dto.getEmployeeId()));
        }
        lesson = repository.update(lesson);
        return mapper.toResponseDTO(lesson);
    }

    @Transactional
    public void deleteDrivingLesson(Long id) {
        repository.delete(id);
    }
}
