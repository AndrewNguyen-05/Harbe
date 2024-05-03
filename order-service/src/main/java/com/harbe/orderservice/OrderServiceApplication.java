package com.harbe.orderservice;

import com.harbe.commons.security.SecurityConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;


@Import(SecurityConfig.class)
@SpringBootApplication
public class OrderServiceApplication {

	@Bean
	public ModelMapper modelMapper() {return new ModelMapper();}
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
