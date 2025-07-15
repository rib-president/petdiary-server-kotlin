package com.petdiary.dto

import com.petdiary.domain.enums.SnsType

data class UserMeDetailResponseDto(
    val displayName: String,
    val emailAddress: String?,
    val avatar: Avatar?,
    val snsType: SnsType,
    val marketingAgreedDateTime: String?
) {
    data class Avatar(
        val key: String,
        val location: String,
        val filename: String,
        val size: Int
    )
}
