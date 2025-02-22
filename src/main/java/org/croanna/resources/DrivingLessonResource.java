package org.croanna.resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.CreateDrivingLessonDTO;
import org.croanna.dtos.DrivingLessonResponseDTO;
import org.croanna.dtos.UpdateDrivingLessonDTO;
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
        List<DrivingLessonResponseDTO> lessons = drivingLessonService.getAllDrivingLessons(zeroBasedPage, size);
        long total = drivingLessonService.getTotal();

        return Response.ok(new PaginatedResponse<>(lessons, total, page, size)).build();
    }

    @GET
    @Path("/{id}")
    public Response getDriver(@PathParam("id") Long id) {
        DrivingLessonResponseDTO driver = drivingLessonService.getDrivingLessonById(id);
        return Response.ok(driver).build();
    }

    @POST
    public Response addDrivingLesson(@Valid CreateDrivingLessonDTO dto) {
        DrivingLessonResponseDTO newLesson = drivingLessonService.createDrivingLesson(dto);
        return Response.status(Response.Status.CREATED)
                .entity(newLesson)
                .build();
    }

    @PATCH
    @Path("/{id}")
    public Response updateDrivingLesson(@PathParam("id") Long id, @Valid UpdateDrivingLessonDTO dto) {
        DrivingLessonResponseDTO updatedDriver = drivingLessonService.updateDrivingLesson(id, dto);
        return Response.ok(updatedDriver)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDrivingLesson(@PathParam("id") Long id) {
        drivingLessonService.deleteDrivingLesson(id);
        return Response.ok().build();
    }
}
