package org.croanna.resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.LoginDTO;
import org.croanna.responses.MessageResponse;
import org.croanna.responses.TokenResponse;
import org.croanna.services.EmployeeService;

@Path("/api/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid final LoginDTO loginDto) {
        if (employeeService.checkUserCredentials(loginDto.getUsername(), loginDto.getPassword())) {
            String token = employeeService.getJwtToken(loginDto.getUsername());
            return Response.ok().entity(new TokenResponse("Bearer " + token, "3600")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new MessageResponse("Invalid credentials")).build();
        }
    }
}
