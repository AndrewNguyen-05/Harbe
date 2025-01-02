package com.harbe.orderservice.controller;

import com.harbe.orderservice.dto.dataIn.OrderBasicInfoDto;
import com.harbe.orderservice.dto.dataOut.*;
import com.harbe.orderservice.service.OrderService;
import com.harbe.orderservice.service.impl.OrderServiceImpl;
import com.harbe.orderservice.utils.AppConstants;
import com.harbe.orderservice.utils.CustomHeaders;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Path("/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(@HeaderParam(CustomHeaders.X_AUTH_USER_ID) long userId,
                                OrderBasicInfoDto orderBasicInfoDto) {
        logger.info("Example log from {}", OrderController.class.getSimpleName());
        return Response.ok(orderService.createOrder(userId, orderBasicInfoDto)).build();
    }

    @POST
    @Path("/paypal_capture/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response capturePaypalOrder(@PathParam("orderId") long orderId) {
        return Response.ok(orderService.capturePaypalOrder(orderId)).build();
    }

    @PUT
    @Path("/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("orderId") long orderId, @Valid OrderDto orderDto) {
        return Response.ok(orderService.updateOrder(orderDto, orderId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrder(@QueryParam("pageNo") @DefaultValue(AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                @QueryParam("pageSize") @DefaultValue(AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                @QueryParam("sortBy") @DefaultValue(AppConstants.DEFAULT_SORT_BY) String sortBy,
                                @QueryParam("sortDir") @DefaultValue(AppConstants.DEFAULT_SORT_DIRECTION) String sortDir) {
        return Response.ok(orderService.getAllOrder(pageNo, pageSize, sortBy, sortDir)).build();
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("orderId") long orderId) {
        return Response.ok(orderService.getOrderById(orderId)).build();
    }

    @DELETE
    @Path("/paypal_capture/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCapturePaypalOrder(@PathParam("orderId") long orderId) {
        return Response.ok(orderService.cancelCapturePaypal(orderId)).build();
    }

    @DELETE
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelOrder(@PathParam("orderId") long orderId) {
        return Response.ok(orderService.cancelOrderById(orderId)).build();
    }
}
