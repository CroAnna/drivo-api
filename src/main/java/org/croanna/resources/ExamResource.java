package org.croanna.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.CreateExamDTO;
import org.croanna.dtos.ExamResponseDTO;
import org.croanna.dtos.UpdateExamDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.ExamService;

import java.util.List;

@PermitAll
@Path("/api/exams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ExamResource {

    @Inject
    ExamService examService;

    @GET
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getAllExams(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        int zeroBasedPage = page - 1;
        List<ExamResponseDTO> exams = examService.getAllExams(zeroBasedPage, size);
        long total = examService.getTotal();

        return Response.ok(new PaginatedResponse<>(exams, total, page, size)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getExam(@PathParam("id") Long id) {
        ExamResponseDTO exam = examService.getExamById(id);
        return Response.ok(exam).build();
    }

    @POST
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response addExam(@Valid CreateExamDTO dto) {
        ExamResponseDTO newExam = examService.createExam(dto);
        return Response.status(Response.Status.CREATED)
                .entity(newExam)
                .build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response updateExam(@PathParam("id") Long id, @Valid UpdateExamDTO dto) {
        ExamResponseDTO updatedDriver = examService.updateExam(id, dto);
        return Response.ok(updatedDriver)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response deleteExam(@PathParam("id") Long id) {
        examService.deleteExam(id);
        return Response.ok().build();
    }
}
