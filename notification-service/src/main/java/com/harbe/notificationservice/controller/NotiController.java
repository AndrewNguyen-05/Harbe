package com.harbe.notificationservice.controller;

import com.harbe.notificationservice.dto.model.NotiDto;
import com.harbe.notificationservice.dto.response.NotiResponseDto;
import com.harbe.notificationservice.dto.response.ObjectResponse;
import com.harbe.notificationservice.service.NotiService;
import com.harbe.notificationservice.utils.AppConstants;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Path("/notifications")
@AllArgsConstructor
public class NotiController {
    private final NotiService notiService;

    @GET
    @Path("/shock/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shockNoti(@PathParam("productId") long productId) {
        NotiDto notiDto = this.notiService.getNotiWithProduct(productId);
        return Response.ok(notiDto).build();
    }

    @GET
    @Path("/{notiId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoti(@PathParam("notiId") long notiId) {
        NotiDto notiDto = this.notiService.getNotificationById(notiId);
        return Response.ok(notiDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotifications(
            @QueryParam("pageNo") @DefaultValue(AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @QueryParam("pageSize") @DefaultValue(AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @QueryParam("sortBy") @DefaultValue(AppConstants.DEFAULT_SORT_BY) String sortBy,
            @QueryParam("sortDir") @DefaultValue(AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        ObjectResponse<NotiDto> response = this.notiService.getAllNotifications(pageNo, pageSize, sortBy, sortDir);
        return Response.ok(response).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNotification(NotiResponseDto notiDto) {
        NotiDto createdNoti = this.notiService.createNotification(notiDto);
        return Response.status(Response.Status.CREATED).entity(createdNoti).build();
    }

    @PUT
    @Path("/{notiId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNotification(
            @PathParam("notiId") long notiId,
            NotiResponseDto notiDto) {
        NotiDto updatedNoti = this.notiService.updateNotification(notiId, notiDto);
        return Response.ok(updatedNoti).build();
    }

    @DELETE
    @Path("/{notiId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteNotification(@PathParam("notiId") long notiId) {
        this.notiService.deleteNotification(notiId);
        return Response.ok("Delete notification successfully!").build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchNoti(
            @QueryParam("title") String title,
            @QueryParam("pageNo") @DefaultValue(AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @QueryParam("pageSize") @DefaultValue(AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @QueryParam("sortBy") @DefaultValue(AppConstants.DEFAULT_SORT_BY) String sortBy,
            @QueryParam("sortDir") @DefaultValue(AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        ObjectResponse<NotiDto> response = this.notiService.searchNoti(title, pageNo, pageSize, sortBy, sortDir);
        return Response.ok(response).build();
    }
}
