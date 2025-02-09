package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.DriverDTO;
import org.croanna.mappers.DriverMapper;
import org.croanna.models.Driver;
import org.croanna.repositories.DriverRepository;

import java.util.List;

@ApplicationScoped
public class DriverService {

    @Inject
    DriverRepository repository;

    @Inject
    DriverMapper mapper;

    public List<DriverDTO> getAllDrivers(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public Long getTotal() {
        return repository.count();
    }


    @Transactional
    public DriverDTO createDriver(DriverDTO dto) {
        Driver vehicle = mapper.toModel(dto);
        vehicle = repository.save(vehicle);
        return mapper.toDTO(vehicle);
    }
}
