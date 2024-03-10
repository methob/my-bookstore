package com.pessoa.jonathan.livraria.debug.repository

import com.pessoa.jonathan.livraria.debug.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.Optional

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmailAndPassword(email: String, password: String): Optional<UserEntity>
    fun findByEmail(email: String): Optional<UserEntity>
    fun deleteByEmail(email: String)
}