package com.consultoria.reuniones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Reuniones - Consultoría")
                        .version("1.0.0")
                        .description("Microservicio encargado de la gestión, agendamiento y control de reuniones de proyectos.")
                        .contact(new Contact()
                                .name("Soporte Consultoría")
                                .email("soporte@consultoria.com")));
    }
}