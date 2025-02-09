package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.InstructorDTO;
import org.croanna.mappers.InstructorMapper;
import org.croanna.models.Instructor;
import org.croanna.repositories.InstructorRepository;

import java.util.List;

@ApplicationScoped
public class InstructorService {

    @Inject
    InstructorRepository repository;

    @Inject
    InstructorMapper mapper;

    public List<InstructorDTO> getAllInstructors(int page, int size, Long categoryId) {
        return repository.findAll(page, size, categoryId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<Instructor> getAllInstructorsByCategory(Long categoryId) {
        return repository.findAll(1, 1000, categoryId).stream().toList();
    }

    public Long getTotal() {
        return repository.count();
    }
}
