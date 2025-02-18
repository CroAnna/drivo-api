package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.CreateDriverDTO;
import org.croanna.dtos.DriverResponseDTO;
import org.croanna.dtos.UpdateDriverDTO;
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

    public List<DriverResponseDTO> getAllDrivers(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public DriverResponseDTO getDriverById(Long id) {
        Driver driver = repository.findById(id);
        return mapper.toResponseDTO(driver);
    }

    public Long getTotal() {
        return repository.count();
    }


    @Transactional
    public DriverResponseDTO createDriver(CreateDriverDTO dto) {
        Driver driver = mapper.toModel(dto);
        driver = repository.save(driver);
        return mapper.toResponseDTO(driver);
    }

    @Transactional
    public DriverResponseDTO updateDriver(Long id, UpdateDriverDTO dto) {
        Driver driver = repository.findById(id);

        if (dto.getPassedPracticalTest() != null) {
            driver.setPassedPracticalTest(dto.getPassedPracticalTest());
        }
        if (dto.getPassedTheoryTest() != null) {
            driver.setPassedTheoryTest(dto.getPassedTheoryTest());
        }
        if (dto.getName() != null) {
            driver.setName(dto.getName());
        }
        if (dto.getHoursDriven() != null) {
            driver.setHoursDriven(dto.getHoursDriven());
        }

        driver = repository.update(driver);
        return mapper.toResponseDTO(driver);
    }

    @Transactional
    public void deleteDriver(Long id) {
        repository.delete(id);
    }
}
