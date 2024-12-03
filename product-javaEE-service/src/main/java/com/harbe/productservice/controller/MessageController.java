package com.harbe.productservice.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@Path("/products/message")
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return message;
    }
}
