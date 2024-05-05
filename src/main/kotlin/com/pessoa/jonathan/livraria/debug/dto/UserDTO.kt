package com.pessoa.jonathan.livraria.debug.dto

import com.pessoa.jonathan.livraria.debug.entity.UserEntity
import io.swagger.v3.oas.annotations.media.Schema
import kotlin.streams.toList

data class UserDTO(
    @Schema
    val id: Long,
    @Schema
    val name: String,
    @Schema
    val email: String,
    @Schema
    val token: String? = null,

    val roles: List<String>? = null
)

fun UserEntity.toUserDTO() : UserDTO = UserDTO(
        id = this.id,
        name = this.name,
        email = this.email,
        roles = this.roles.stream().map {
            it.name
        }.toList()
)