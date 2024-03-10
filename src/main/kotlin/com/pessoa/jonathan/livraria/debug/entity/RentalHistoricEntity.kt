package com.pessoa.jonathan.livraria.debug.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "rental_historic_bookstore")
data class RentalHistoricEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // varias locacoes podem ser feitas por um unico usuario
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: UserEntity,

    // varias locacoes podem ser feitas para um unico livro
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    val book: BookEntity,

    @Temporal(TemporalType.TIMESTAMP)
    val rentalDate: Date,

    @Temporal(TemporalType.TIMESTAMP)
    var returnDate: Date? = null
)