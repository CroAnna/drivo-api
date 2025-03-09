package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.dtos.CreateEmployeeDTO;
import org.croanna.dtos.EmployeeResponseDTO;
import org.croanna.models.Employee;

@ApplicationScoped
public class EmployeeMapper {
    public EmployeeResponseDTO toResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getPhone(),
                employee.getUsername(),
                employee.getRole()
        );
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
