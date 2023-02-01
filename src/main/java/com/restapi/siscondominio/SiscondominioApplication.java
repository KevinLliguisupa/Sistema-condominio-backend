package com.restapi.siscondominio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SiscondominioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiscondominioApplication.class, args);
	}
	@GetMapping
	public String greeting(){
		return "Bienvenido al Rest API SisCondominio V1";
	}

}
