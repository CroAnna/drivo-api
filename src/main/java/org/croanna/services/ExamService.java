package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.ExamDTO;
import org.croanna.mappers.ExamMapper;
import org.croanna.models.Exam;
import org.croanna.repositories.ExamRepository;

import java.util.List;

@ApplicationScoped
public class ExamService {
    @Inject
    ExamRepository repository;

    @Inject
    ExamMapper mapper;

    public List<ExamDTO> getAllExams(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public Long getTotal() {
        return repository.count();
    }


    @Transactional
    public ExamDTO createExam(ExamDTO dto) {
        Exam exam = mapper.toModel(dto);
        exam = repository.save(exam);
        return mapper.toDTO(exam);
    }
}
