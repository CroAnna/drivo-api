package org.croanna.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.CreateEmployeeDTO;
import org.croanna.dtos.EmployeeResponseDTO;
import org.croanna.dtos.LoginDTO;
import org.croanna.dtos.UpdateEmployeeDTO;
import org.croanna.responses.MessageResponse;
import org.croanna.responses.PaginatedResponse;
import org.croanna.responses.TokenResponse;
import org.croanna.services.EmployeeService;

import java.util.List;

@Path("/api/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @GET
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getAllInstructors(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("category_id") Long categoryId
    ) {
        int zeroBasedPage = page - 1;
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees(zeroBasedPage, size);
        long total = employeeService.getTotal();

        return Response.ok(new PaginatedResponse<>(employees, total, page, size)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getInstructor(@PathParam("id") Long id) {
        EmployeeResponseDTO employee = employeeService.getEmployeeById(id);
        return Response.ok(employee).build();
    }

    @POST
    @Path("/login")
    public Response login(@Valid final LoginDTO loginDto) {
        if (employeeService.checkUserCredentials(loginDto.getUsername(), loginDto.getPassword())) {
            String token = employeeService.getJwtToken(loginDto.getUsername());
            return Response.ok().entity(new TokenResponse(token, "3600")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new MessageResponse("Invalid credentials")).build();
        }
    }

    @POST
    @Path("/register")
    public Response register(@Valid CreateEmployeeDTO dto) {
        EmployeeResponseDTO newEmployee = employeeService.createEmployee(dto);
        return Response.status(Response.Status.CREATED)
                .entity(newEmployee)
                .build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response updateEmployee(@PathParam("id") Long id, @Valid UpdateEmployeeDTO dto) {
        EmployeeResponseDTO updatedEmployee = employeeService.updateEmployee(id, dto);
        return Response.ok(updatedEmployee)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response deleteEmployee(@PathParam("id") Long id) {
        employeeService.deleteEmployee(id);
        return Response.ok().build();
    }
}
