package com.pessoa.jonathan.livraria.debug.dto

import com.pessoa.jonathan.livraria.debug.entity.BookEntity
import com.pessoa.jonathan.livraria.debug.entity.UserEntity
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

class BookDTO(
    @Schema
    val id: Long? = null,
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

fun BookEntity.toBookDTO() : BookDTO = BookDTO(this.id, this.name, this.author, this.description, this.gender)

enum class BookStatusEnum {
    BORROWED, FREE;
    companion object {
        private val map = values().associateBy(BookStatusEnum::name)
        fun fromString(type: String) = map[type]
    }
}