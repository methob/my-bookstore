package com.pessoa.jonathan.livraria.debug.controller

import com.pessoa.jonathan.livraria.debug.dto.*
import com.pessoa.jonathan.livraria.debug.exception.BookStoreException
import com.pessoa.jonathan.livraria.debug.service.UserService
import com.pessoa.jonathan.livraria.debug.utils.JwtUtils
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.headers.Header
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService,
                     private val passwordEncoder: BCryptPasswordEncoder) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Salva usuario")
    fun createUser(@Valid @RequestBody payload: UserPayloadDTO)  {
        userService.saveUser(payload.name, payload.password, payload.email)
    }

    @PostMapping("/login")
    @Operation(description = "Loga o Usuário")
    fun login(@Valid @RequestBody payload: UserPayloadLoginDTO) : ResponseEntity<UserDTO> {
        val user = userService.findUserByEmail(payload.email)
        return if (user.isPresent) {
            val userPresent = user.get()
            if (passwordEncoder.matches(payload.password, userPresent.password)) {
                val token = JwtUtils.generateToken(payload.email)
                ResponseEntity.ok(UserDTO(userPresent.id, userPresent.name,userPresent.email, token))
            } else {
                throw BookStoreException("Usuário não encontrado", HttpStatus.UNAUTHORIZED)
            }
        } else {
            throw BookStoreException("Usuário não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/list")
    @Operation(description = "lista usuários ( temporário, vou criar roles)")
    fun listUsers() : List<UserDTO> {
        return userService.listUsers().stream().map {
            it.toUserDTO()
        }.collect(Collectors.toList())
    }

    @PostMapping("/update")
    @Operation(description = "Atualiza usuários ( temporário, vou criar roles)")
    fun updateUser()  {

    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @Operation(description = "deleta usuarios do banco de dados ( temporário, vou criar roles)")
    fun removeUser(@RequestHeader email: String) {
        userService.removeUserByEmail(email)
    }
}