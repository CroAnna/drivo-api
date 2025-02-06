package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.VehicleDTO;
import org.croanna.mappers.VehicleMapper;
import org.croanna.repositories.VehicleRepository;

import java.util.List;

@ApplicationScoped
public class VehicleService {

    @Inject
    VehicleRepository repository;

    @Inject
    VehicleMapper mapper;

    public List<VehicleDTO> getAllVehicles(int page, int size) {
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
