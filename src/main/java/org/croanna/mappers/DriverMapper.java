package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.CreateDriverDTO;
import org.croanna.dtos.DriverResponseDTO;
import org.croanna.models.Driver;
import org.croanna.repositories.CategoryRepository;

@ApplicationScoped
public class DriverMapper {

    @Inject
    CategoryRepository categoryRepository;

    public DriverResponseDTO toResponseDTO(Driver driver) {
        return new DriverResponseDTO(
                driver.getId(),
                driver.getName(),
                driver.getPassedPracticalTest(),
                driver.getPassedTheoryTest(),
                driver.getHoursDriven(),
                driver.getPhone(),
                driver.getCategory() != null ? driver.getCategory().getId() : null
        );
    }

    public Driver toModel(CreateDriverDTO driver) {
        return new Driver(
                driver.getId(),
                driver.getName(),
                driver.getPassedPracticalTest(),
                driver.getPassedTheoryTest(),
                driver.getHoursDriven(),
                driver.getPhone(),
                driver.getCategoryId() != null ?
                        categoryRepository.findById(driver.getCategoryId()) : null
        );
    }
}
