package com.pessoa.jonathan.livraria.debug

import com.pessoa.jonathan.livraria.debug.utils.loadDotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DebugApplication
fun main(args: Array<String>) {
	loadDotenv("debug.env")
	runApplication<DebugApplication>(*args)
}

