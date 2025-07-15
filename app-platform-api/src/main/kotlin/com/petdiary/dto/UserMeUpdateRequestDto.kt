package com.petdiary.dto

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime
import java.util.Optional

data class UserMeUpdateRequestDto(
    @field:NotBlank
    val displayName: String,
    val marketingAgreedDateTime: Optional<LocalDateTime>? = null,
    val avatar: Optional<Avatar>? = null
) {
    data class Avatar(
        val key: String,
        val location: String,
        val filename: String,
        val size: Int
    )
}
