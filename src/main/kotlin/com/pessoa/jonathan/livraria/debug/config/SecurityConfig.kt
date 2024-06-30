package com.pessoa.jonathan.livraria.debug.config

import com.pessoa.jonathan.livraria.debug.jwt.JwtAuthenticationFilter
import com.pessoa.jonathan.livraria.debug.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(val userService: UserService)  {

    @Bean
    fun configure(http: HttpSecurity) : SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { customizer ->
                with(customizer) {
                    requestMatchers("/user/list", "/user/delete").hasRole("BOOKSTORE_ADMIN")
                    .requestMatchers(*swaggerURL).permitAll()
                    .requestMatchers("/user/**").permitAll()
                    .anyRequest().authenticated()
                }
            }
            .addFilterBefore(JwtAuthenticationFilter(userService), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    companion object {
        val swaggerURL  = arrayOf("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html")
    }
}