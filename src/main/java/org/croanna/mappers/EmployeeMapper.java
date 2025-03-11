package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.dtos.CreateEmployeeDTO;
import org.croanna.dtos.EmployeeResponseDTO;
import org.croanna.models.Category;
import org.croanna.models.Employee;
import org.croanna.services.CategoryService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeMapper {

    @Inject
    CategoryService categoryService;

    public EmployeeResponseDTO toResponseDTO(Employee employee) {

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getPhone(),
                employee.getUsername(),
                employee.getRole(),
                employee.getAvailability(),
                employee.getCategories().stream()
                        .map(Category::getTitle)
                        .collect(Collectors.toSet())
        );

    }

    public Employee toModel(CreateEmployeeDTO employee) {
        Set<Category> categories = new HashSet<>(categoryService.getCategoriesDataByTitle(employee.getCategories()));
        return new Employee(
                employee.getId(),
                employee.getUsername(),
                employee.getPassword(),
                employee.getName(),
                employee.getPhone(),
                employee.getRole(),
                employee.getAvailability(),
                categories
        );
    }
}
