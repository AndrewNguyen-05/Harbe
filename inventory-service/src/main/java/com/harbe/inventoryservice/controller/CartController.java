package com.harbe.inventoryservice.controller;

import com.harbe.inventoryservice.dto.model.ProductDto;
import com.harbe.inventoryservice.dto.request.CartItemRequest;
import com.harbe.inventoryservice.dto.request.ProductCartDeletionRequest;
import com.harbe.inventoryservice.dto.request.UpdateCartRequest;
import com.harbe.inventoryservice.service.CartRedisService;
import com.harbe.inventoryservice.utils.CustomHeaders;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Cart",
        description = "REST APIs for Cart"
)

@Component
@Path("/carts")
@AllArgsConstructor
public class CartController {
    private final CartRedisService cartRedisService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsFromCart(@HeaderParam(CustomHeaders.X_AUTH_USER_ID) String userId) {
        List<ProductDto> products = this.cartRedisService.getProductsFromCart(userId);
        return Response.ok(products).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addProductToCart(
            @HeaderParam(CustomHeaders.X_AUTH_USER_ID) String userId,
            CartItemRequest item
    ) {
        this.cartRedisService.addProductToCart(userId, item);
        return Response.status(Response.Status.CREATED).entity("Add to cart successfully!").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProductInCart(
            @HeaderParam(CustomHeaders.X_AUTH_USER_ID) String userId,
            UpdateCartRequest item
    ) {
        this.cartRedisService.updateProductInCart(userId, item);
        return Response.ok("Update cart successfully!").build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProductInCart(
            @HeaderParam(CustomHeaders.X_AUTH_USER_ID) String userId,
            ProductCartDeletionRequest item
    ) {
        this.cartRedisService.deleteProductInCart(userId, item);
        return Response.ok("Product deleted from cart successfully!").build();
    }

    @DELETE
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAllProductsInCart(@HeaderParam(CustomHeaders.X_AUTH_USER_ID) String userId) {
        this.cartRedisService.deleteAllProductsInCart(userId);
        return Response.ok("Delete all products in cart successfully!").build();
    }

    @GET
    @Path("/info")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserInfo(
            @HeaderParam(CustomHeaders.X_AUTH_USER_ID) String userId,
            @HeaderParam(CustomHeaders.X_AUTH_USER_AUTHORITIES) String authorities
    ) {
        String userInfo = "User ID: " + userId + ", Authorities: " + authorities;
        return Response.ok(userInfo).build();
    }
}
