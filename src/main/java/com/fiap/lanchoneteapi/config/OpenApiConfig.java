package com.fiap.lanchoneteapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Lanchonete")
                        .version("v1")
                        .description("API Lanchonete MBA Engenharia de Software FIAP/SP ")
                        .termsOfService("Terms Of Service")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/fransoufil/lanchonete-api")));
    }
}
