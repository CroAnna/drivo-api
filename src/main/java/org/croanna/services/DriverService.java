package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.DriverDTO;
import org.croanna.mappers.DriverMapper;
import org.croanna.repositories.DriverRepository;

import java.util.List;

@ApplicationScoped
public class DriverService {

    @Inject
    DriverRepository repository;

    @Inject
    DriverMapper mapper;

    public List<DriverDTO> getAllDrivers(int page, int size) {
        return repository.findAll()
                .page(page, size)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public Long getTotal() {
        return repository.count();
    }
}
