package com.dani.spring.ecommerce_backend_api.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("controllers")
                .packagesToScan("com.dani.spring.ecommerce_backend_api.controllers")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecommerce API")
                        .version("1.0")
                        .description("API REST para ecommerce desarrollada con Spring Boot 3.5.10"))
                // Agregar el requisito de seguridad global
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                // Configurar el esquema de seguridad JWT
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", 
                            new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                                .description("Ingresa tu token JWT (sin 'Bearer')")
                        )
                );
    }

}
