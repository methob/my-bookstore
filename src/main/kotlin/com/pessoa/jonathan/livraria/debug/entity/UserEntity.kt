package com.pessoa.jonathan.livraria.debug.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_bookstore", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    var email: String,
    val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    val roles : MutableList<RoleEntity> = ArrayList()
)




