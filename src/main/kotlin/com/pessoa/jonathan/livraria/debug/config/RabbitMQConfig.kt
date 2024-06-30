package com.pessoa.jonathan.livraria.debug.config

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Bean
    fun queue(): Queue {
        return Queue("TesteJonathanQUEUE", false)
    }
}