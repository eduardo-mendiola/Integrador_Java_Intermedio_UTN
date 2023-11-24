package com.tpIntegrador.IncidentManager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Incident Manager")
                        .version("1.0")
                        .description("App para gestionar reportes de incidentes")
                        .termsOfService("https://github.com/eduardo-mendiola/Integrador_Java_Intermedio_UTN")
                        .license(new License().name("MIT").url("http"))
                );
    }

}
