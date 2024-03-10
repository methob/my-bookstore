package com.pessoa.jonathan.livraria.debug.entity

import jakarta.persistence.*

@Entity
@Table(name = "book_bookstore")
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val author: String,
    val description: String,
    val gender: String,
    var status: String
)