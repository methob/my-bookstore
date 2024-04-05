package com.pessoa.jonathan.livraria.debug.exception

import com.pessoa.jonathan.livraria.debug.dto.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@Component
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BookStoreException::class)
    fun handleGlobalException(ex: BookStoreException) : ResponseEntity<ErrorDTO> {
        val error = ErrorDTO(ex.message, HttpStatus.BAD_REQUEST)
        return ResponseEntity<ErrorDTO>(error, error.code)
    }

    @ExceptionHandler
    fun handleGlobalException(ex: Exception) : ResponseEntity<ErrorDTO> {
        val error = ErrorDTO(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
        return ResponseEntity<ErrorDTO>(error, error.code)
    }
}