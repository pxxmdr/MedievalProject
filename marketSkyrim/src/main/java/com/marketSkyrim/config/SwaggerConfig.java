package com.marketSkyrim.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Market Skyrim API")
                        .version("1.0")
                        .description("API para gerenciamento de personagens e itens do universo Skyrim")
                        .contact(new Contact()
                                .name("Adonay Rodrigues da Rocha")
                                .name("Pedro Henrique Martins dos Reis")
                                .email("rm558782@fiap.com.br")));
    }
}
