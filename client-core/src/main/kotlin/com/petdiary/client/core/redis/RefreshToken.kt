package com.petdiary.client.core.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash("refresh_token")
data class RefreshToken(
    @Id
    val userId: String,

    val token: String,

    val expiry: Long,

    @TimeToLive
    @Value("\${jwt.refresh-expire-time}")
    val ttl: Long
)
