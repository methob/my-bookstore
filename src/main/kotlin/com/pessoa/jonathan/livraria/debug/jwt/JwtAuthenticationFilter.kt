package com.pessoa.jonathan.livraria.debug.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.pessoa.jonathan.livraria.debug.dto.ErrorDTO
import com.pessoa.jonathan.livraria.debug.entity.RoleEntity
import com.pessoa.jonathan.livraria.debug.exception.BookStoreException
import com.pessoa.jonathan.livraria.debug.service.UserService
import com.pessoa.jonathan.livraria.debug.utils.JwtUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors


class JwtAuthenticationFilter(private val userService: UserService) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = request.getHeader("Authorization")
        runCatching {
            if (!token.isNullOrEmpty()) {
                runCatching {
                    val subject = JwtUtils.validateToken(token)
                    if (subject.isNotEmpty()) {
                        val user = userService.findUserByEmail(subject)
                        if (user.isPresent) {
                            val authentication = UsernamePasswordAuthenticationToken(
                                    user.get(), null, mapRolesToAuthorities(user.get().roles))
                            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                            SecurityContextHolder.getContext().authentication = authentication
                        }
                    }
                }
            } else {
                throw BookStoreException("Deu ruim")
            }
        }.onFailure { ex ->
            val error = ErrorDTO(ex.message, HttpStatus.BAD_REQUEST)
            response.status = error.code.value()
            response.contentType = "application/json"
            response.writer.println(ObjectMapper().writeValueAsString(error))
            return
        }

        filterChain.doFilter(request, response)
    }

    private fun mapRolesToAuthorities(roles: List<RoleEntity>) : List<SimpleGrantedAuthority> {
        return roles.stream().map { role -> SimpleGrantedAuthority(role.name) }.collect(Collectors.toList())
    }
}