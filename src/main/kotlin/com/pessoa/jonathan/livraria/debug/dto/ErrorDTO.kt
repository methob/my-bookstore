package com.pessoa.jonathan.livraria.debug.dto

import org.springframework.http.HttpStatus

data class ErrorDTO (val message: String?,
                     val code: HttpStatus)