package com.pessoa.jonathan.livraria.debug.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtUtils {

    private const val EXPIRATION_TIME: Long = 864000000 // 10 days
    private const val SECRET = "jonathan-secret"

    fun generateToken(subject: String) : String {
        return JWT.create().withSubject(subject)
                .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.toByteArray()));
    }

    fun validateToken(token: String): String {
        return JWT.require(Algorithm.HMAC512(SECRET.toByteArray())).build().verify(token).subject
    }
}