package com.restapi.siscondominio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class SiscondominioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiscondominioApplication.class, args);
	}

	@GetMapping
	public String greeting() {
		return "Bienvenido al Rest API SisCondominio V1";
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/login")
								.allowedOrigins("http://localhost:4200")
										.allowedMethods("*")
												.exposedHeaders("*");

				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*");
			}
		};


	}
}
