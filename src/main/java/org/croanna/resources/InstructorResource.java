package org.croanna.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.CreateInstructorDTO;
import org.croanna.dtos.InstructorResponseDTO;
import org.croanna.dtos.UpdateInstructorDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.InstructorService;

import java.util.List;

@PermitAll
@Path("/api/instructors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstructorResource {

    @Inject
    InstructorService instructorService;

    @GET
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getAllInstructors(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("category_id") Long categoryId
    ) {
        int zeroBasedPage = page - 1;
        List<InstructorResponseDTO> instructors = instructorService.getAllInstructors(zeroBasedPage, size, categoryId);
        long total = instructorService.getTotal();

        return Response.ok(new PaginatedResponse<>(instructors, total, page, size)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getInstructor(@PathParam("id") Long id) {
        InstructorResponseDTO instructor = instructorService.getInstructorById(id);
        return Response.ok(instructor).build();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response addInstructor(@Valid CreateInstructorDTO dto) {
        InstructorResponseDTO newInstructor = instructorService.createInstructor(dto);
        return Response.status(Response.Status.CREATED)
                .entity(newInstructor)
                .build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response updateInstructor(@PathParam("id") Long id, @Valid UpdateInstructorDTO dto) {
        InstructorResponseDTO updatedInstructor = instructorService.updateInstructor(id, dto);
        return Response.ok(updatedInstructor)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response deleteInstructor(@PathParam("id") Long id) {
        instructorService.deleteInstructor(id);
        return Response.ok().build();
    }

}
