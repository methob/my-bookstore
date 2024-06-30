package com.pessoa.jonathan.livraria.debug.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun getOpenAPI(): OpenAPI {
        return OpenAPI()
                .info(Info().title("Livraria"))
                .addSecurityItem(SecurityRequirement().addList("api_key"))
                .components(Components()
                        .addSecuritySchemes("api_key", SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.APIKEY)
                                .`in`(SecurityScheme.In.HEADER))
                )
    }
}