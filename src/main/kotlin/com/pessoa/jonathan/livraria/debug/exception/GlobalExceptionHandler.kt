package com.pessoa.jonathan.livraria.debug.exception

import com.pessoa.jonathan.livraria.debug.dto.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import kotlin.Exception

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BookStoreException::class)
    fun handleGlobalException(ex: BookStoreException) : ResponseEntity<ErrorDTO> {
        val error = ErrorDTO(ex.message, ex.code)
        return ResponseEntity<ErrorDTO>(error, error.code)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(ex: Exception) : ResponseEntity<ErrorDTO> {
        val error = ErrorDTO(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
        return ResponseEntity<ErrorDTO>(error, error.code)
    }
}