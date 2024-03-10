package com.pessoa.jonathan.livraria.debug.service

import com.pessoa.jonathan.livraria.debug.entity.BookEntity
import com.pessoa.jonathan.livraria.debug.entity.RentalHistoricEntity
import com.pessoa.jonathan.livraria.debug.entity.UserEntity
import com.pessoa.jonathan.livraria.debug.repository.BookRepository
import com.pessoa.jonathan.livraria.debug.repository.RentalHistoricRepository
import com.pessoa.jonathan.livraria.debug.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RentalHistoricService(
    val rentalHistoricRepository: RentalHistoricRepository,
    val bookRepository: BookRepository,
    val userRepository: UserRepository
) {

    fun getUserAndBook(userId: Long, bookId: Long): Pair<Optional<UserEntity>, Optional<BookEntity>> {
        val user = userRepository.findById(userId)
        val book = bookRepository.findById(bookId)
        return Pair(user, book)
    }

    fun getHistoric(userId: Long, bookId: Long) = rentalHistoricRepository.findByUserIdAndBookId(userId, bookId)

    fun updateHistoric(rentalHistoricEntity: RentalHistoricEntity) {
        rentalHistoricRepository.save(rentalHistoricEntity)
    }

    fun createHistoric(userEntity: UserEntity, bookEntity: BookEntity) {
        rentalHistoricRepository.save(RentalHistoricEntity(
                user = userEntity,
                book = bookEntity,
                rentalDate = Date()))
    }
}