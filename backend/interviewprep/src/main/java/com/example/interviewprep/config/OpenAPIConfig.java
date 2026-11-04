package com.example.interviewprep.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title ="Interview Preparation Platform API",
        version="1.0",
        description="APIs for Interview Preparation Platform"
    )
)
@SecurityScheme(
    name ="bearerAuth",
    type =SecuritySchemeType.HTTP,
    bearerFormat="JWT",
    scheme="bearer"
)
public class OpenAPIConfig {
}