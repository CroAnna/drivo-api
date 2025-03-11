package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.CreateExamDTO;
import org.croanna.dtos.ExamResponseDTO;
import org.croanna.dtos.UpdateExamDTO;
import org.croanna.mappers.ExamMapper;
import org.croanna.models.Exam;
import org.croanna.repositories.DriverRepository;
import org.croanna.repositories.EmployeeRepository;
import org.croanna.repositories.ExamRepository;

import java.util.List;

@ApplicationScoped
public class ExamService {
    @Inject
    ExamRepository repository;

    @Inject
    DriverRepository driverRepository;

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    ExamMapper mapper;

    public List<ExamResponseDTO> getAllExams(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public ExamResponseDTO getExamById(Long id) {
        Exam exam = repository.findById(id);
        return mapper.toResponseDTO(exam);
    }

    public Long getTotal() {
        return repository.count();
    }


    @Transactional
    public ExamResponseDTO createExam(CreateExamDTO dto) {
        Exam exam = mapper.toModel(dto);
        exam = repository.save(exam);
        return mapper.toResponseDTO(exam);
    }

    @Transactional
    public ExamResponseDTO updateExam(Long id, UpdateExamDTO dto) {
        Exam exam = repository.findById(id);

        if (dto.getType() != null) {
            exam.setType(dto.getType());
        }
        if (dto.getDate() != null) {
            exam.setDate(dto.getDate());
        }
        if (dto.getStatus() != null) {
            exam.setStatus(dto.getStatus());
        }
        if (dto.getDriverId() != null) {
            exam.setDriver(driverRepository.findById(dto.getDriverId()));
        }
        if (dto.getEmployeeId() != null) {
            exam.setEmployee(employeeRepository.findById(dto.getEmployeeId()));
        }
        exam = repository.update(exam);
        return mapper.toResponseDTO(exam);
    }

    @Transactional
    public void deleteExam(Long id) {
        repository.delete(id);
    }
}
