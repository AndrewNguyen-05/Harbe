package com.harbe.notificationservice.config;

import com.harbe.notificationservice.controller.HelloWorldEndpoint;
import com.harbe.notificationservice.controller.NotiController;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api/v1")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HelloWorldEndpoint.class);
        register(NotiController.class);
    }

}

