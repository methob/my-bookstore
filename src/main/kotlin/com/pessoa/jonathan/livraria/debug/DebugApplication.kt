package com.pessoa.jonathan.livraria.debug

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class DebugApplication

fun main(args: Array<String>) {
	runApplication<DebugApplication>(*args)
}

