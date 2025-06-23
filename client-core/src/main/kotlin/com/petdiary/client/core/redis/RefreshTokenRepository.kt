package com.petdiary.client.core.redis

import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
    fun findByUserId(userId: String): RefreshToken?
}