package org.croanna.services;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.dtos.CreateEmployeeDTO;
import org.croanna.dtos.EmployeeResponseDTO;
import org.croanna.dtos.UpdateEmployeeDTO;
import org.croanna.mappers.EmployeeMapper;
import org.croanna.models.Employee;
import org.croanna.repositories.EmployeeRepository;

import java.util.List;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

    @Inject
    EmployeeMapper mapper;

    public List<EmployeeResponseDTO> getAllEmployees(int page, int size) {
        return repository.findAll(page, size)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = repository.findById(id);
        return mapper.toResponseDTO(employee);
    }

    public Long getTotal() {
        return repository.count();
    }


    @Transactional
    public EmployeeResponseDTO createEmployee(CreateEmployeeDTO dto) {
        Employee employee = mapper.toModel(dto);
        employee.setPassword(BcryptUtil.bcryptHash(employee.getPassword()));
        employee = repository.save(employee);
        return mapper.toResponseDTO(employee);
    }

    public String generateJwtToken(final Employee employee) {
        String issuer = "https://example.com/issuer";
        return Jwt.issuer(issuer)
                .upn(employee.getUsername())
                .groups(employee.getRole().name())
                .expiresIn(3600)
                .sign();
    }

    public Boolean checkUserCredentials(String username, String password) {
        Employee employee = repository.findByUsername(username);
        if (employee == null) {
            return false;
        }
        return BcryptUtil.matches(password, employee.getPassword());
    }

    public String getJwtToken(String username) {
        Employee employee = repository.findByUsername(username);
        return generateJwtToken(employee);
    }

    @Transactional
    public EmployeeResponseDTO updateEmployee(Long id, UpdateEmployeeDTO dto) {
        Employee employee = repository.findById(id);

        if (dto.getName() != null) {
            employee.setName(dto.getName());
        }
        if (dto.getPhone() != null) {
            employee.setPhone(dto.getPhone());
        }
        if (dto.getUsername() != null) {
            employee.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            employee.setPassword(BcryptUtil.bcryptHash(dto.getPassword()));
        }

        employee = repository.update(employee);
        return mapper.toResponseDTO(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        repository.delete(id);
    }


}
