package com.pessoa.jonathan.livraria.debug.dto

import com.pessoa.jonathan.livraria.debug.entity.UserEntity
import io.swagger.v3.oas.annotations.media.Schema

data class UserDTO(
    @Schema
    val id: Long,
    @Schema
    val name: String,
    @Schema
    val email: String,
    @Schema
    val token: String? = null,

    val roles: String?
)

fun UserEntity.toUserDTO() : UserDTO = UserDTO(this.id, this.name, this.email, roles = this.roles?.getOrNull(0)?.name)