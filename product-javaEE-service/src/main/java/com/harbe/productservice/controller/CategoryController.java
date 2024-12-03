package com.harbe.productservice.controller;

import com.harbe.productservice.dto.message.CategoryResponseDto;
import com.harbe.productservice.dto.model.CategoryDto;
import com.harbe.productservice.dto.model.CreateCategoryDto;
import com.harbe.productservice.dto.response.ObjectResponse;
import com.harbe.productservice.service.CategoryService;
import com.harbe.productservice.utils.AppConstants;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Component;

@Tag(
        name = "Category",
        description = "REST APIs for Product"
)
@Component
@Path("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(CreateCategoryDto createCategoryDto) {
        CategoryDto createdCategory = this.categoryService.createCategory(createCategoryDto);
        return Response.status(Response.Status.CREATED).entity(createdCategory).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories(
            @QueryParam("pageNo") @DefaultValue(AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @QueryParam("pageSize") @DefaultValue("12") int pageSize,
            @QueryParam("sortBy") @DefaultValue(AppConstants.DEFAULT_SORT_BY) String sortBy,
            @QueryParam("sortDir") @DefaultValue(AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        ObjectResponse<CategoryResponseDto> response = this.categoryService.getAllCategories(pageNo, pageSize, sortBy, sortDir);
        return Response.ok(response).build();
    }

    @GET
    @Path("/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("categoryId") Long id) {
        CategoryDto category = this.categoryService.getCategoryById(id);
        return Response.ok(category).build();
    }

    @PUT
    @Path("/{categoryId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("categoryId") Long id, CreateCategoryDto createCategoryDto) {
        CategoryDto updatedCategory = this.categoryService.updateCategory(createCategoryDto, id);
        return Response.ok(updatedCategory).build();
    }

    @DELETE
    @Path("/{categoryId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCategory(@PathParam("categoryId") Long id) {
        this.categoryService.deleteCategory(id);
        return Response.ok("Delete category successfully!").build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCategory(
            @QueryParam("name") String name,
            @QueryParam("pageNo") @DefaultValue(AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @QueryParam("pageSize") @DefaultValue(AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @QueryParam("sortBy") @DefaultValue(AppConstants.DEFAULT_SORT_BY) String sortBy,
            @QueryParam("sortDir") @DefaultValue(AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        ObjectResponse<CategoryResponseDto> response = this.categoryService.searchCategory(name, pageNo, pageSize, sortBy, sortDir);
        return Response.ok(response).build();
    }
}

