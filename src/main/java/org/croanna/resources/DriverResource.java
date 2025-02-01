package org.croanna.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.models.Driver;
import org.croanna.services.DriverService;

import java.util.List;

    @Path("/api/drivers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public class DriverResource {

        @Inject
        DriverService driverService;

        @GET
        public Response getAllDrivers() {
            List<Driver> drivers = driverService.getAllDrivers();
            return Response.ok(drivers).build();
        }
    }

