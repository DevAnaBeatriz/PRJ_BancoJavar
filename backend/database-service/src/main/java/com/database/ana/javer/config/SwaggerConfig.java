package com.database.ana.javer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.context.annotation.Configuration;



@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Clientes - Banco JAVER - Segunda Aplicação",
                version = "1.0",
                description = "API responsável pelo armazenamento e gerenciamento de clientes do banco JAVER.",
                contact = @Contact(
                        name = "Suporte API",
                        email = "suporte@anabancojaver.com"
                )
        ),
        servers = {@Server(url = "http://localhost:8080", description = "Servidor Local"),}
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {}


