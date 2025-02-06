package org.croanna.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.models.Vehicle;

@ApplicationScoped
public class VehicleRepository implements PanacheRepository<Vehicle> {
}
