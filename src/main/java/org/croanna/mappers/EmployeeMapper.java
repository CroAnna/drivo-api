package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.dtos.CreateEmployeeDTO;
import org.croanna.dtos.EmployeeResponseDTO;
import org.croanna.dtos.InstructorResponseDTO;
import org.croanna.models.Employee;
import org.croanna.models.Instructor;

import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeMapper {
    public EmployeeResponseDTO toResponseDTO(Employee employee) {
        if (employee instanceof Instructor instructor) {
            return new InstructorResponseDTO(
                    instructor.getId(),
                    instructor.getName(),
                    instructor.getPhone(),
                    instructor.getUsername(),
                    instructor.getRole(),
                    instructor.getAvailability(),
                    instructor.getCategories().stream()
                            .map(category -> category.getTitle())
                            .collect(Collectors.toSet())
            );
        } else {
            return new EmployeeResponseDTO(
                    employee.getId(),
                    employee.getName(),
                    employee.getPhone(),
                    employee.getUsername(),
                    employee.getRole()
            );
        }
    }

    public Employee toModel(CreateEmployeeDTO employee) {
        return new Employee(
                employee.getId(),
                employee.getUsername(),
                employee.getPassword(),
                employee.getName(),
                employee.getPhone(),
                employee.getRole()
        );
    }
}
