package com.pessoa.jonathan.livraria.debug.service

import com.pessoa.jonathan.livraria.debug.entity.UserEntity
import com.pessoa.jonathan.livraria.debug.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional


@Service
class UserService(private val userRepository: UserRepository,
                  private val passwordEncoder: BCryptPasswordEncoder) {

    fun saveUser(name: String, password: String, email: String): UserEntity {
        val encryptedPassword = passwordEncoder.encode(password)
        return userRepository.save(UserEntity(name = name, email = email, password = encryptedPassword))
    }

    fun saveUser(user: UserEntity) = userRepository.save(user)

    fun findUserByEmail(email: String): Optional<UserEntity> {
        return userRepository.findByEmail(email)
    }

    fun removeUserByEmail(email: String) {
        userRepository.deleteByEmail(email)
    }

    fun findUserByNameAndPassword(email: String, password: String) : Optional<UserEntity> {
        val encryptedPassword = passwordEncoder.encode(password)
        return userRepository.findByEmailAndPassword(email, encryptedPassword)
    }

    fun listUsers(): List<UserEntity> = userRepository.findAll()
}