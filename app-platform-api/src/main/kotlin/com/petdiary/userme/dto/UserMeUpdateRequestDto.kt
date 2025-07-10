package com.petdiary.userme.dto

import java.time.LocalDateTime

data class UserMeUpdateRequestDto(
    val displayName: String? = null,
    val marketingAgreedDateTime: LocalDateTime? = null,
    val avatar: Avatar? = null
) {
    data class Avatar(
        val key: String,
        val location: String,
        val filename: String,
        val size: Int
    )
}
