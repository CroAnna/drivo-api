package org.croanna.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.CategoryDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.CategoryService;

import java.util.List;

@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {
    @Inject
    CategoryService categoryService;

    @GET
    public Response getAllCategories(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        int zeroBasedPage = page - 1;
        List<CategoryDTO> categories = categoryService.getAllCategories(zeroBasedPage, size);
        long total = categoryService.getTotal();

        return Response.ok(new PaginatedResponse<>(categories, total, page, size)).build();
    }
}
