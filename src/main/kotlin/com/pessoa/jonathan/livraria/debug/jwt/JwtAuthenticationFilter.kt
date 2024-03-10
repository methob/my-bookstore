package com.pessoa.jonathan.livraria.debug.jwt

import com.pessoa.jonathan.livraria.debug.service.UserService
import com.pessoa.jonathan.livraria.debug.utils.JwtUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter


class JwtAuthenticationFilter(private val userService: UserService) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = request.getHeader("Authorization")
        if (!token.isNullOrEmpty()) {
            runCatching {
                val subject = JwtUtils.validateToken(token)
                if (subject.isNotEmpty()) {
                    val user = userService.findUserByEmail(subject)
                    if (user.isPresent) {
                        val authentication = UsernamePasswordAuthenticationToken(user.get(), null, null)
                        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authentication
                    }
                }
            }
        }
        filterChain.doFilter(request, response)
    }
}