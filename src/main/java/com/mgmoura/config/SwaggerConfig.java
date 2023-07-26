package com.mgmoura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenApi() {

		return new OpenAPI().components(new Components()).info(
				new Info()
					.title("API para Seleção Dev Java - Stefanini Campina Grande")
					.description("Desenvolvido com Spring Boot e Spring Data")
					.version("v1.0")
				);
	}
}

