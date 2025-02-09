package org.croanna.resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.DriverDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.DriverService;

import java.util.List;

@Path("/api/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverResource {

    @Inject
    DriverService driverService;

    @GET
    public Response getAllDrivers(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        int zeroBasedPage = page - 1;
        List<DriverDTO> drivers = driverService.getAllDrivers(zeroBasedPage, size);
        long total = driverService.getTotal();

        return Response.ok(new PaginatedResponse<>(drivers, total, page, size)).build();
    }

    @POST
    public Response addDriver(@Valid DriverDTO dto) {
        DriverDTO newDriver = driverService.createDriver(dto);
        return Response.status(Response.Status.CREATED)
                .entity(newDriver)
                .build();
    }
}

