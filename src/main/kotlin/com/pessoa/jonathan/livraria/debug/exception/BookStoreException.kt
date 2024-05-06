package com.pessoa.jonathan.livraria.debug.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

class BookStoreException(message: String = "", val code: HttpStatus = HttpStatus.BAD_REQUEST) : RuntimeException(message)