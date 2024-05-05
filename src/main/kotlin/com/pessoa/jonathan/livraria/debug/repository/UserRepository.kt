package com.pessoa.jonathan.livraria.debug.repository

import com.pessoa.jonathan.livraria.debug.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmailAndPassword(email: String, password: String): Optional<UserEntity>
    fun findByEmail(email: String): Optional<UserEntity>
}