package org.croanna.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.croanna.dtos.CategoryResponseDTO;
import org.croanna.dtos.CreateCategoryDTO;
import org.croanna.dtos.UpdateCategoryDTO;
import org.croanna.responses.PaginatedResponse;
import org.croanna.services.CategoryService;

import java.util.List;

@PermitAll
@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {
    @Inject
    CategoryService categoryService;

    @GET
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getAllCategories(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("instructor_id") Long instructorId
    ) {
        int zeroBasedPage = page - 1;
        List<CategoryResponseDTO> categories = categoryService.getAllCategories(zeroBasedPage, size, instructorId);
        long total = categoryService.getTotal();

        return Response.ok(new PaginatedResponse<>(categories, total, page, size)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "INSTRUCTOR"})
    public Response getCategory(@PathParam("id") Long id) {
        CategoryResponseDTO category = categoryService.getCategoryById(id);
        return Response.ok(category).build();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response addCategory(@Valid CreateCategoryDTO dto) {
        CategoryResponseDTO newCategory = categoryService.createCategory(dto);
        return Response.status(Response.Status.CREATED)
                .entity(newCategory)
                .build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response updateCategory(@PathParam("id") Long id, @Valid UpdateCategoryDTO dto) {
        CategoryResponseDTO updatedCategory = categoryService.updateCategory(id, dto);
        return Response.ok(updatedCategory)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response deleteCategory(@PathParam("id") Long id) {
        categoryService.deleteCategory(id);
        return Response.ok().build();
    }
}
