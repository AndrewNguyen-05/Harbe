package com.harbe.notificationservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class NotificationServiceApplication {
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
