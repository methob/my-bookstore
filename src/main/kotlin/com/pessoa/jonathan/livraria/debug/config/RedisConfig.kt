package com.pessoa.jonathan.livraria.debug.config

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableCaching
class RedisConfig {

    @Bean
    fun getRedisConnectionFactory() = LettuceConnectionFactory()

    @Bean
    fun getRedisTemplate(redisConnectionFactory: RedisConnectionFactory) : RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            connectionFactory = redisConnectionFactory
            keySerializer = StringRedisSerializer()
            valueSerializer = GenericJackson2JsonRedisSerializer()
        }
    }
}