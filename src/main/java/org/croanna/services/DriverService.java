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

    public List<DriverDTO> getAllDrivers() {
        return repository.findAllWithCategory().stream()
                .map(mapper::toDTO)
                .toList();
    }
}
