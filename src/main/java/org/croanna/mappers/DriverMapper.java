package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.DriverDTO;
import org.croanna.models.Driver;

@ApplicationScoped
public class DriverMapper {

    @Inject
    CategoryMapper categoryMapper;

    public DriverDTO toDTO(Driver driver) {
        return new DriverDTO(
                driver.getId(),
                driver.getName(),
                driver.isPassedPracticalTest(),
                driver.isPassedTheoryTest(),
                driver.getHoursDriven(),
                driver.getPhone(),
                driver.getCategory() != null ? categoryMapper.toDTO(driver.getCategory()) : null
        );
    }
}
