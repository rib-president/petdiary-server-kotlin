package com.petdiary.client.core.filter

import com.petdiary.client.core.provider.JwtProvider
import com.petdiary.client.core.redis.RefreshTokenRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class InitializeFilter (private val jwtProvider: JwtProvider,
    private val refreshTokenRepository: RefreshTokenRepository
    ): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authorization = request.getHeader("Authorization")
        val token = authorization
            ?.takeIf { it.startsWith("Bearer ") }
            ?.substring("Bearer ".length)
            ?.let { accessToken ->
                if(jwtProvider.validateToken(accessToken)) {
                    accessToken
                } else {
                    val userId = jwtProvider.getClaims(accessToken).subject
                    val refreshToken = refreshTokenRepository.findByUserId(userId)?.token
                    refreshToken?.let { jwtProvider.getAccessToken(userId) }
                        ?: throw BadCredentialsException("expired token")
                }
            }

        token?.let {
            val authentication: Authentication = jwtProvider.getAuthentication(it)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)

    }
}