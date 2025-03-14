package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.DatasetResponseDTO;
import org.croanna.dtos.PersonPerCategoryResponseDTO;
import org.croanna.repositories.DriverRepository;
import org.croanna.repositories.EmployeeRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@ApplicationScoped
public class StatsService {
    @Inject
    DriverRepository driverRepository;

    @Inject
    EmployeeRepository employeeRepository;

    public DatasetResponseDTO getActiveDriversPerCategory() {
        return convertToDatasetResponse(driverRepository::getActiveDriversPerCategory); // == () -> driverRepository.getActiveDriversPerCategory()
    }

    public DatasetResponseDTO getInstructorsPerCategory() {
        return convertToDatasetResponse(employeeRepository::getInstructorsPerCategory);
    }

    private DatasetResponseDTO convertToDatasetResponse(Supplier<List<PersonPerCategoryResponseDTO>> dataSupplier) {
        List<PersonPerCategoryResponseDTO> personsPerCategory = dataSupplier.get();

        List<String> labels = personsPerCategory.stream()
                .map(PersonPerCategoryResponseDTO::getCategoryName)
                .collect(Collectors.toList());

        List<Long> data = personsPerCategory.stream()
                .map(PersonPerCategoryResponseDTO::getTotalPersons)
                .collect(Collectors.toList());

        return new DatasetResponseDTO(labels, data);
    }
}
