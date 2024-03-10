package com.pessoa.jonathan.livraria.debug.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class BookPayloadDTO(
    @Schema
    @NotBlank(message = "Field is mandatory")
    val name: String,
    @NotBlank(message = "Field is mandatory")
    @Schema
    val author: String,
    @Schema
    val description: String,
    @NotBlank(message = "Field is mandatory")
    @Schema
    val gender: String
)