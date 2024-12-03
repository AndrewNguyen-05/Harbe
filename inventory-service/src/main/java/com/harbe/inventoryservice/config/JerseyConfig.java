package com.harbe.inventoryservice.config;

import com.harbe.inventoryservice.controller.CartController;
import com.harbe.inventoryservice.controller.HelloWorldEndpoint;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api/v1/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HelloWorldEndpoint.class);
        register(CartController.class);
    }

}

