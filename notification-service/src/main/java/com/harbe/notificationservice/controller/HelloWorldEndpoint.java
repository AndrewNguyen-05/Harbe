package com.harbe.notificationservice.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.springframework.stereotype.Component;

@Component
@Path("/jaxrs/hello")
public class HelloWorldEndpoint {
    @GET
    public String message() {
        return "Hello World";
    }
}