package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.DatasetResponseDTO;
import org.croanna.dtos.DriverPerCategoryResponseDTO;
import org.croanna.repositories.DriverRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class StatsService {
    @Inject
    DriverRepository driverRepository;

    public DatasetResponseDTO getActiveDriversPerCategory() {
        List<DriverPerCategoryResponseDTO> driversPerCategory = driverRepository.getActiveDriversPerCategory();

        Set<String> labels = driversPerCategory.stream()
                .map(DriverPerCategoryResponseDTO::getCategoryName)
                .collect(Collectors.toSet());

        List<Long> data = driversPerCategory.stream()
                .map(DriverPerCategoryResponseDTO::getTotalDrivers)
                .collect(Collectors.toList());

        return new DatasetResponseDTO(labels, data);
    }

}
