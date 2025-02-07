package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.InstructorDTO;
import org.croanna.mappers.InstructorMapper;
import org.croanna.repositories.InstructorRepository;

import java.util.List;

@ApplicationScoped
public class InstructorService {

    @Inject
    InstructorRepository repository;

    @Inject
    InstructorMapper mapper;

    public List<InstructorDTO> getAllInstructors(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public Long getTotal() {
        return repository.count();
    }
}
