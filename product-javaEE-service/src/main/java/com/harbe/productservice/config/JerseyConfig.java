package com.harbe.productservice.config;

import com.harbe.productservice.controller.CategoryController;
import com.harbe.productservice.controller.HelloWorldEndpoint;
import com.harbe.productservice.controller.MessageController;
import com.harbe.productservice.controller.ProductController;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api/v1")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HelloWorldEndpoint.class);
        register(ProductController.class);
        register(CategoryController.class);
        register(MessageController.class);
    }

}

