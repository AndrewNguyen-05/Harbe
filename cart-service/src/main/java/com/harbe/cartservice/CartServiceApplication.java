package com.harbe.cartservice;

import com.harbe.cartservice.security.SecurityConfig;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Cart Service REST APIs",
				description = "Cart Service REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Nguyen Van Hoang Anh",
						email = "anhnguyen.052003@gmail.com",
						url = "https://github.com/AndrewNguyen-05"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/AndrewNguyen-05"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Cart-Service Doc",
				url = "https://github.com/AndrewNguyen-05"
		)
)

@Import(SecurityConfig.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CartServiceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

}
