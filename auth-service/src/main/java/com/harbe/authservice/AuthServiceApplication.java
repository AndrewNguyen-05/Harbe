package com.harbe.authservice;

import com.harbe.commons.persistence.AuditingConfig;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@OpenAPIDefinition(
		info = @Info(
				title = "Auth Service REST APIs",
				description = "Auth Service REST APIs Documentation",
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
				description = "Auth-Service Doc",
				url = "https://github.com/AndrewNguyen-05"
		)
)

@Import(AuditingConfig.class)
@SpringBootApplication
public class AuthServiceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
