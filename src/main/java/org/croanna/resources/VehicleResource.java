package org.croanna.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.CreateVehicleDTO;
import org.croanna.dtos.UpdateVehicleDTO;
import org.croanna.dtos.VehicleResponseDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.VehicleService;

import java.util.List;

@PermitAll
@Path("/api/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleResource {

    @Inject
    VehicleService vehicleService;

    @GET
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getAllVehicles(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        int zeroBasedPage = page - 1;
        List<VehicleResponseDTO> vehicles = vehicleService.getAllVehicles(zeroBasedPage, size);
        long total = vehicleService.getTotal();

        return Response.ok(new PaginatedResponse<>(vehicles, total, page, size)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getVehicle(@PathParam("id") Long id) {
        VehicleResponseDTO vehicle = vehicleService.getVehicleById(id);
        return Response.ok(vehicle).build();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response addVehicle(@Valid CreateVehicleDTO dto) {
        VehicleResponseDTO newVehicle = vehicleService.createVehicle(dto);
        return Response.status(Response.Status.CREATED)
                .entity(newVehicle)
                .build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response updateVehicle(@PathParam("id") Long id, @Valid UpdateVehicleDTO dto) {
        VehicleResponseDTO updatedVehicle = vehicleService.updateVehicle(id, dto);
        return Response.ok(updatedVehicle)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response deleteVehicle(@PathParam("id") Long id) {
        vehicleService.deleteVehicle(id);
        return Response.ok().build();
    }
}
