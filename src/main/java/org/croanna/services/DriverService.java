package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.croanna.models.Driver;
import org.croanna.repositories.DriverRepository;

import java.util.List;

@ApplicationScoped
public class DriverService {

    @Inject
    DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.listAll();
    }
}
