package com.example.Hospital.Management.System.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration //This class contains project configuration.
@OpenAPIDefinition(
        //Sets Swagger page details:
        //
        //Title
        //Version
        //Description
        info = @Info(
                title = "Hospital Management System API",
                version = "1.0",
                description = "Hospital Management System with JWT Authentication"
        )
)
@SecurityScheme(  //This project uses JWT Bearer Token authentication.
        //Because of this annotation Swagger shows:
        //🔒 Authorize
        //button.
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}