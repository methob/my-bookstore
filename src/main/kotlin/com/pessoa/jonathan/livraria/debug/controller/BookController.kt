package com.pessoa.jonathan.livraria.debug.controller

import com.pessoa.jonathan.livraria.debug.dto.*
import com.pessoa.jonathan.livraria.debug.service.BookService
import com.pessoa.jonathan.livraria.debug.service.RentalHistoricService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/book")
class BookController(
        val bookService: BookService,
        val rentalHistoricService: RentalHistoricService) {

    @GetMapping("/list")
    @Operation(description = "lista livros")
    fun listBooks(): List<BookDTO>  {
        return bookService.listAllBooks().stream().map {
            it.toBookDTO()
        }.collect(Collectors.toList())
    }

    @PostMapping("/save")
    @Operation(description = "cadastra livro")
    fun saveBook(@Valid @RequestBody payload: BookPayloadDTO): ResponseEntity<BookDTO>  {
        val book = bookService.registerBook(payload.name, payload.author, payload.description, payload.gender)
        return ResponseEntity.status(HttpStatus.CREATED).body(BookDTO(id = book.id,
                name = book.name, author = book.author, description = book.description, book.gender))
    }

    // TODO Alterar para alugar livros em lote
    @PostMapping("/rent")
    @Operation(description = "aluga um livro")
    fun rentBook(@Valid @RequestBody payload: RentPayloadDTO)  {
        val pairUserAndBook = rentalHistoricService.getUserAndBook(payload.userId, payload.bookId)
        val book = pairUserAndBook.second
        val user = pairUserAndBook.first
        if (user.isPresent && book.isPresent) {
            val bookEnt = book.get()
            if (BookStatusEnum.fromString(bookEnt.status) == BookStatusEnum.FREE) {
                bookEnt.status = BookStatusEnum.BORROWED.name
                bookService.saveBook(bookEnt)
                rentalHistoricService.createHistoric(user.get(), bookEnt)
            }
        } else {
            // TODO TRATAR
        }
    }

    @PostMapping("/return")
    @Operation(description = "devolve o livro")
    fun returnBook(@Valid @RequestBody payload: RentPayloadDTO)  {
        val pairUserAndBook = rentalHistoricService.getUserAndBook(payload.userId, payload.bookId)
        val book = pairUserAndBook.second
        val user = pairUserAndBook.first
        if (user.isPresent && book.isPresent) {
            val bookEnt = book.get()
            if (BookStatusEnum.fromString(bookEnt.status) == BookStatusEnum.BORROWED) {
                bookEnt.status = BookStatusEnum.FREE.name
                bookService.saveBook(bookEnt)
                val historic = rentalHistoricService.getHistoric(payload.userId, payload.bookId)
                historic.map { item ->
                    historic.get().returnDate = Date()
                    rentalHistoricService.updateHistoric(item)
                }.orElseGet {
                    // TODO TRATAR
                    throw Exception("Deu ruim")
                }
            }
        } else {
            // TODO TRATAR
        }
    }
}