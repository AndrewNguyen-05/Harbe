package com.harbe.productservice.controller;

import com.harbe.productservice.dto.message.ProductDetailResponseDto;
import com.harbe.productservice.dto.model.ProductDto;
import com.harbe.productservice.dto.response.ObjectResponse;
import com.harbe.productservice.dto.response.ProductWithOptionForCartDto;
import com.harbe.productservice.service.ProductService;
import com.harbe.productservice.utils.AppConstants;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Product",
        description = "REST APIs for Product"
)

@Component
@Path("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(@Valid ProductDto productDto) {
        ProductDto createdProduct = this.productService.createProduct(productDto);
        return Response.status(Response.Status.CREATED).entity(createdProduct).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts(
            @QueryParam("pageNo") @DefaultValue(AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @QueryParam("pageSize") @DefaultValue(AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @QueryParam("sortBy") @DefaultValue(AppConstants.DEFAULT_SORT_BY) String sortBy,
            @QueryParam("sortDir") @DefaultValue(AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        ObjectResponse<ProductDto> response = this.productService.getAllProducts(pageNo, pageSize, sortBy, sortDir);
        return Response.ok(response).build();
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("productId") Long id) {
        ProductDetailResponseDto productDetail = this.productService.getProductById(id);
        return Response.ok(productDetail).build();
    }

    @PUT
    @Path("/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@Valid ProductDto productDto, @PathParam("productId") Long productId) {
        ProductDto updatedProduct = this.productService.updateProduct(productDto, productId);
        return Response.ok(updatedProduct).build();
    }

    @DELETE
    @Path("/{productId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProduct(@PathParam("productId") Long productId) {
        this.productService.deleteProduct(productId);
        return Response.ok("Product is deleted successfully!").build();
    }

    @POST
    @Path("/product-options")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProductByProductOptionId(String idLists) {
        ProductWithOptionForCartDto productOptions = this.productService.getProductByProductOptionId(idLists);
        return Response.ok(productOptions).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchProduct(
            @QueryParam("name") String name,
            @QueryParam("pageNo") @DefaultValue(AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @QueryParam("pageSize") @DefaultValue(AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @QueryParam("sortBy") @DefaultValue(AppConstants.DEFAULT_SORT_BY) String sortBy,
            @QueryParam("sortDir") @DefaultValue(AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        ObjectResponse<ProductDto> response = this.productService.searchProduct(name, pageNo, pageSize, sortBy, sortDir);
        return Response.ok(response).build();
    }
}

