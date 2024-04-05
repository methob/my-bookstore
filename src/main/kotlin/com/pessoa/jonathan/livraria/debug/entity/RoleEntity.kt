package com.pessoa.jonathan.livraria.debug.entity

import jakarta.persistence.*


@Entity
@Table(name = "role_bookstore")
class RoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    val name: String
)