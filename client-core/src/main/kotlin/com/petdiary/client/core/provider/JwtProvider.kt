package com.petdiary.client.core.provider

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.util.Date
import javax.crypto.SecretKey
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class JwtProvider (
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.issuer}")
    private val issuer: String,

    @Value("\${jwt.expire-time}")
    private val expireTime: Long,
    @Value("\${jwt.refresh-expire-time}")
    private val refreshExpireTime: Long,

    private val userDetailsService: UserDetailsService
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))

    fun getAccessToken(userId: String): String {
        return this.issueToken(userId, expireTime)
    }

    fun getRefreshToken(userId: String): String {
        return this.issueToken(userId, refreshExpireTime)
    }

    fun issueToken(userId: String, expireTime: Long): String {
        val now = Date()
        val expiry = Date(now.time + expireTime)    // 3 hours

        return Jwts.builder()
            .subject(userId)
            .issuer(issuer)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(secretKey)
            .compact()
    }

    fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = this.getClaims(token)

            val expiration = claims.expiration
            expiration.after(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)
        val userId = claims.subject
        val userDetails = userDetailsService.loadUserByUsername(userId)

        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }
}