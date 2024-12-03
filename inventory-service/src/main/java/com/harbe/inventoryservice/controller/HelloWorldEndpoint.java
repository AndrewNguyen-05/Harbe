package com.harbe.inventoryservice.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.springframework.stereotype.Component;

@Component
@Path("/jaxrs-cart/hello")
public class HelloWorldEndpoint {
    @GET
    public String message() {
        return "Hello World";
    }
}