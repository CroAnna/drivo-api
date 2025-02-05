package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.dtos.DriverDTO;
import org.croanna.models.Driver;

@ApplicationScoped
public class DriverMapper {
    public DriverDTO toDTO(Driver driver) {
        return new DriverDTO(
                driver.id,
                driver.name,
                driver.passedPracticalTest,
                driver.passedTheoryTest,
                driver.hoursDriven,
                driver.phone,
                driver.category != null ? driver.category.id : null
        );
    }
}
