package org.croanna.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.ExamDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.ExamService;

import java.util.List;

@Path("/api/exams")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ExamResource {
    @Inject
    ExamService examService;

    @GET
    public Response getAllExams(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        int zeroBasedPage = page - 1;
        List<ExamDTO> exams = examService.getAllExams(zeroBasedPage, size);
        long total = examService.getTotal();

        return Response.ok(new PaginatedResponse<>(exams, total, page, size)).build();
    }
}
