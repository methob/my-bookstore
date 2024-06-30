package com.pessoa.jonathan.livraria.debug.service

import com.pessoa.jonathan.livraria.debug.dto.BookStatusEnum
import com.pessoa.jonathan.livraria.debug.entity.BookEntity
import com.pessoa.jonathan.livraria.debug.repository.BookRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun registerBook(name: String, author: String, description: String, gender: String): BookEntity {
        return bookRepository.save(BookEntity(name = name,
                author = author, description = description, gender = gender,
                status = BookStatusEnum.FREE.name))
    }

    fun saveBook(bookEntity: BookEntity) {
        bookRepository.save(bookEntity)
    }

    @Cacheable(value = ["books"], key = "my_books")
    fun listAllBooks(): List<BookEntity> {
        return bookRepository.findAll()
    }
}