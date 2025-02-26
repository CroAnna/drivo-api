package org.croanna.services;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.models.Employee;
import org.croanna.repositories.EmployeeRepository;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

    public String generateJwtToken(final Employee employee) {
        String issuer = "https://example.com/issuer";
        return Jwt.issuer(issuer)
                .upn(employee.getUsername())
                .groups(employee.getRole().name())
                .expiresIn(3600)
                .sign();
    }

    public Boolean checkUserCredentials(String username, String password) {
        return repository.checkUserCredentials(username, password);
    }

    public String getJwtToken(String username) {
        Employee employee = repository.findByUsername(username);
        return generateJwtToken(employee);
    }
}
