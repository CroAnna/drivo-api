package org.croanna.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.DrivingLessonDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.DrivingLessonService;

import java.util.List;

@Path("/api/driving-lessons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DrivingLessonResource {

    @Inject
    DrivingLessonService drivingLessonService;

    @GET
    public Response getAllDrivingLessons(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        int zeroBasedPage = page - 1;
        List<DrivingLessonDTO> lessons = drivingLessonService.getAllDrivingLessons(zeroBasedPage, size);
        long total = drivingLessonService.getTotal();

        return Response.ok(new PaginatedResponse<>(lessons, total, page, size)).build();
    }
}
