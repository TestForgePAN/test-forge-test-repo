package com.travelapp.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSwagger2
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	

}
