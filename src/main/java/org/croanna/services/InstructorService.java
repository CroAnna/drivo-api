package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.CreateInstructorDTO;
import org.croanna.dtos.InstructorResponseDTO;
import org.croanna.dtos.UpdateInstructorDTO;
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

    public List<InstructorResponseDTO> getAllInstructors(int page, int size, Long categoryId) {
        return repository.findAll(page, size, categoryId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public List<Instructor> getAllInstructorsByCategory(Long categoryId) {
        return repository.findAll(1, 1000, categoryId).stream().toList();
    }

    public InstructorResponseDTO getInstructorById(Long id) {
        Instructor instructor = repository.findById(id);
        return mapper.toResponseDTO(instructor);
    }

    public Long getTotal() {
        return repository.count();
    }

    @Transactional
    public InstructorResponseDTO createInstructor(CreateInstructorDTO dto) {
        Instructor instructor = mapper.toModel(dto);
        instructor = repository.save(instructor);
        return mapper.toResponseDTO(instructor);
    }

    @Transactional
    public InstructorResponseDTO updateInstructor(Long id, UpdateInstructorDTO dto) {
        Instructor instructor = repository.findById(id);

        if (dto.getName() != null) {
            instructor.setName(dto.getName());
        }
        if (dto.getPhone() != null) {
            instructor.setPhone(dto.getPhone());
        }
        if (dto.getAvailability() != null) {
            instructor.setAvailability(dto.getAvailability());
        }

        instructor = repository.update(instructor);
        return mapper.toResponseDTO(instructor);
    }

    @Transactional
    public void deleteInstructor(Long id) {
        repository.delete(id);
    }
}
