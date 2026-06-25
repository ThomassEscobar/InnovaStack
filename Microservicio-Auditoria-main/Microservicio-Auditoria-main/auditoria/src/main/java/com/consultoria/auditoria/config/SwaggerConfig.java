package com.consultoria.auditoria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info().title("Microservicio De Auditoria")
                            .version("v1")
                            .description("Documentacion de la Api de Auditoria de sistema de gestion de consultoria InnovaStack"));
    }




}
