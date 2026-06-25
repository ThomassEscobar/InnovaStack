package com.consultoria.reportes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Reportes y Métricas - Consultoría")
                        .version("1.0.0")
                        .description("Microservicio analítico encargado de compilar métricas financieras y operacionales en Dashboards unificados.")
                        .contact(new Contact()
                                .name("Soporte de Business Intelligence")
                                .email("bi.soporte@consultoria.com")));
    }
}