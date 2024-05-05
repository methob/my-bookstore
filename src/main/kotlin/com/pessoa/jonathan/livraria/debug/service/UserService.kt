package com.pessoa.jonathan.livraria.debug.service

import com.pessoa.jonathan.livraria.debug.entity.UserEntity
import com.pessoa.jonathan.livraria.debug.repository.RoleRepository
import com.pessoa.jonathan.livraria.debug.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional


@Service
class UserService(private val userRepository: UserRepository,
                  val roleRepository: RoleRepository,
                  private val passwordEncoder: BCryptPasswordEncoder) {

    fun saveUser(name: String, password: String, email: String): UserEntity {
        val encryptedPassword = passwordEncoder.encode(password)
        val role = roleRepository.findByName("BOOKSTORE_USER").get()
        return userRepository.save(UserEntity(name = name, email = email, password = encryptedPassword, roles = mutableListOf(role)))
    }

    fun saveUser(user: UserEntity) = userRepository.save(user)

    fun findUserByEmail(email: String): Optional<UserEntity> {
        return userRepository.findByEmail(email)
    }

    @Transactional
    fun removeUserByEmail(email: String) {
        val user = userRepository.findByEmail(email)
        if (user.isPresent) {
            userRepository.delete(user.get())
        }
    }

    fun findUserByNameAndPassword(email: String, password: String) : Optional<UserEntity> {
        val encryptedPassword = passwordEncoder.encode(password)
        return userRepository.findByEmailAndPassword(email, encryptedPassword)
    }

    fun listUsers(): List<UserEntity> = userRepository.findAll()
}