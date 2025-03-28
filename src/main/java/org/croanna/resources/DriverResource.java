package org.croanna.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.CreateDriverDTO;
import org.croanna.dtos.DriverResponseDTO;
import org.croanna.dtos.UpdateDriverDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.DriverService;

import java.util.List;

@PermitAll
@Path("/api/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverResource {

    @Inject
    DriverService driverService;

    @GET
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getAllDrivers(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        int zeroBasedPage = page - 1;
        List<DriverResponseDTO> drivers = driverService.getAllDrivers(zeroBasedPage, size);
        long total = driverService.getTotal();

        return Response.ok(new PaginatedResponse<>(drivers, total, page, size)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getDriver(@PathParam("id") Long id) {
        DriverResponseDTO driver = driverService.getDriverById(id);
        return Response.ok(driver).build();
    }

    @POST
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public Response addDriver(@Valid CreateDriverDTO dto) {
        DriverResponseDTO newDriver = driverService.createDriver(dto);
        return Response.status(Response.Status.CREATED)
                .entity(newDriver)
                .build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public Response updateDriver(@PathParam("id") Long id, @Valid UpdateDriverDTO dto) {
        DriverResponseDTO updatedDriver = driverService.updateDriver(id, dto);
        return Response.ok(updatedDriver)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response deleteDriver(@PathParam("id") Long id) {
        driverService.deleteDriver(id);
        return Response.ok().build();
    }
}

