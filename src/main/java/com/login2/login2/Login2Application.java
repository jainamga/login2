package com.login2.login2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Login2Application {

	public static void main(String[] args) {
		SpringApplication.run(Login2Application.class, args);
	}
	@Bean
	 ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
