package com.client.ana.javer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.servers.Server;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Cliente - Primeira Aplicação",
                version = "1.0",
                description = "Gerenciamento de Clientes e Conexão com a Segunda Aplicação",
                contact = @Contact(
                        name = "Suporte API",
                        email = "suporte@anaclientejaver.com"
                )
        ),
        servers = {@Server(url = "http://localhost:8081", description = "Servidor Local"),}
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {}

