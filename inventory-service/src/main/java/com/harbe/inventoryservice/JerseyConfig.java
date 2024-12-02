package com.harbe.inventoryservice;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api/v1/jaxrs-cart")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HelloWorldEndpoint.class);
    }

}

