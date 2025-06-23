package com.petdiary.client.core.filter

import com.petdiary.client.core.provider.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class InitializeFilter (private val jwtProvider: JwtProvider): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authorization: String? = request.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer ")) {
            val token = authorization.substring("Bearer ".length)
            if (!jwtProvider.validateToken(token)) {
                throw BadCredentialsException("expired token")
            }

            val authentication: Authentication = jwtProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)

    }
}