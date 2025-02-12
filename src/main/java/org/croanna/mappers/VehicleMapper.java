package org.croanna.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.dtos.CreateVehicleDTO;
import org.croanna.dtos.VehicleResponseDTO;
import org.croanna.models.Vehicle;

@ApplicationScoped
public class VehicleMapper {

    public VehicleResponseDTO toResponseDTO(Vehicle vehicle) {
        return new VehicleResponseDTO(
                vehicle.getId(),
                vehicle.getLicencePlate(),
                vehicle.getModel(),
                vehicle.getYear()
        );
    }

    public Vehicle toModel(CreateVehicleDTO vehicle) {
        return new Vehicle(
                vehicle.getId(),
                vehicle.getLicencePlate(),
                vehicle.getModel(),
                vehicle.getYear()
        );
    }


}
