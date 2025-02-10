package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.DrivingLessonDTO;
import org.croanna.mappers.DrivingLessonMapper;
import org.croanna.models.DrivingLesson;
import org.croanna.repositories.DrivingLessonRepository;

import java.util.List;

@ApplicationScoped
public class DrivingLessonService {

    @Inject
    DrivingLessonRepository repository;

    @Inject
    DrivingLessonMapper mapper;

    public List<DrivingLessonDTO> getAllDrivingLessons(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public Long getTotal() {
        return repository.count();
    }

    @Transactional
    public DrivingLessonDTO createLesson(DrivingLessonDTO dto) {
        DrivingLesson lesson = mapper.toModel(dto);
        lesson = repository.save(lesson);
        return mapper.toDTO(lesson);
    }
}
