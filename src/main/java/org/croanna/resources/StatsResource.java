package org.croanna.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.ChartDatasetResponseDTO;
import org.croanna.dtos.DatasetResponseDTO;
import org.croanna.responses.ChartResponse;
import org.croanna.services.StatsService;

import java.util.Collections;
import java.util.List;

@PermitAll
@Path("/api/stats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatsResource {

    @Inject
    StatsService statsService;

    @GET
    @Path("/active-drivers-per-category")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getActiveDriversPerCategory() {
        DatasetResponseDTO datasetResponse = statsService.getActiveDriversPerCategory();
        ChartDatasetResponseDTO chartDataset = new ChartDatasetResponseDTO("Number of drivers per category", datasetResponse.getData());
        List<ChartDatasetResponseDTO> chartDatasetList = Collections.singletonList(chartDataset);
        return Response.ok(new ChartResponse(datasetResponse.getLabel(), chartDatasetList)).build();
    }

    @GET
    @Path("/instructors-per-category")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getInstructorsPerCategory() {
        DatasetResponseDTO datasetResponse = statsService.getInstructorsPerCategory();
        ChartDatasetResponseDTO chartDataset = new ChartDatasetResponseDTO("Number of instructors per category", datasetResponse.getData());
        List<ChartDatasetResponseDTO> chartDatasetList = Collections.singletonList(chartDataset);
        return Response.ok(new ChartResponse(datasetResponse.getLabel(), chartDatasetList)).build();
    }
}
