package org.croanna.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.InstructorDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.InstructorService;

import java.util.List;

@Path("/api/instructors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstructorResource {

    @Inject
    InstructorService instructorService;

    @GET
    public Response getAllInstructors(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("category_id") Long categoryId
    ) {
        int zeroBasedPage = page - 1;
        List<InstructorDTO> instructors = instructorService.getAllInstructors(zeroBasedPage, size, categoryId);
        long total = instructorService.getTotal();

        return Response.ok(new PaginatedResponse<>(instructors, total, page, size)).build();
    }

}
