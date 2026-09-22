package com.example.DevShowcase.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DevShowcase API")
                        .version("1.0.0")
                        .description("Documentação oficial da API RESTful desenvolvida com Spring Boot 3 para exibição de portefólios de programadores."));
    }
}
