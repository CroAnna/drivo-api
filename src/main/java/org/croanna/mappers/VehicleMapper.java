package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.dtos.VehicleDTO;
import org.croanna.models.Vehicle;

@ApplicationScoped
public class VehicleMapper {

    public VehicleDTO toDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getLicencePlate(),
                vehicle.getModel(),
                vehicle.getYear()
        );
    }
}
