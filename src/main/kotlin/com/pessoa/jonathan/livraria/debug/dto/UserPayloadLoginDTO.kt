package com.pessoa.jonathan.livraria.debug.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserPayloadLoginDTO(
        @Schema
        @NotBlank(message = "Field is mandatory")
        val email: String,

        @Size(min = 8, message = "Password must be at least 8 characters")
        @NotBlank(message = "Field is mandatory")
        @Schema
        val password: String
)