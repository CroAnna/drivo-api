package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.CreateVehicleDTO;
import org.croanna.dtos.UpdateVehicleDTO;
import org.croanna.dtos.VehicleResponseDTO;
import org.croanna.mappers.VehicleMapper;
import org.croanna.models.Vehicle;
import org.croanna.repositories.VehicleRepository;

import java.util.List;

@ApplicationScoped
public class VehicleService {

    @Inject
    VehicleRepository repository;

    @Inject
    VehicleMapper mapper;

    public List<VehicleResponseDTO> getAllVehicles(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public Long getTotal() {
        return repository.count();
    }

    @Transactional
    public VehicleResponseDTO createVehicle(CreateVehicleDTO dto) {
        Vehicle vehicle = mapper.toModel(dto);
        vehicle = repository.save(vehicle);
        return mapper.toResponseDTO(vehicle);
    }

    @Transactional
    public VehicleResponseDTO updateVehicle(Long id, UpdateVehicleDTO dto) {
        Vehicle vehicle = repository.findById(id);

        if (dto.getLicencePlate() != null) {
            vehicle.setLicencePlate(dto.getLicencePlate());
        }
        if (dto.getModel() != null) {
            vehicle.setModel(dto.getModel());
        }
        if (dto.getYear() != null) {
            vehicle.setYear(dto.getYear());
        }

        vehicle = repository.update(vehicle);
        return mapper.toResponseDTO(vehicle);
    }

    @Transactional
    public void deleteVehicle(Long id) {
        repository.delete(id);
    }
}
