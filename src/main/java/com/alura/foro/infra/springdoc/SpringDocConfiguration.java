package com.alura.foro.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Alura Foro")
                        .description("API Rest de la aplicación Alura foro, que contiene las funcionalidades de CRUD de topicos.")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("backend@alura.foro"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://alura.foro/api/licencia")));
    }

        @Bean
        public String message(){
            return "Bearer is working";
        }
    }


