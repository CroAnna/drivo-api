package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.DriverDTO;
import org.croanna.models.Driver;
import org.croanna.repositories.CategoryRepository;

@ApplicationScoped
public class DriverMapper {

    @Inject
    CategoryRepository categoryRepository;

    public DriverDTO toDTO(Driver driver) {
        return new DriverDTO(
                driver.getId(),
                driver.getName(),
                driver.isPassedPracticalTest(),
                driver.isPassedTheoryTest(),
                driver.getHoursDriven(),
                driver.getPhone(),
                driver.getCategory() != null ? driver.getCategory().getId() : null
        );
    }

    public Driver toModel(DriverDTO driver) {
        return new Driver(
                driver.getId(),
                driver.getName(),
                driver.isPassedPracticalTest(),
                driver.isPassedTheoryTest(),
                driver.getHoursDriven(),
                driver.getPhone(),
                driver.getCategoryId() != null ?
                        categoryRepository.findById(driver.getCategoryId()) : null
        );
    }
}
