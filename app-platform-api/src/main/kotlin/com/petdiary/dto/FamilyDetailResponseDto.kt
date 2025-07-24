package com.petdiary.dto

data class FamilyDetailResponseDto(
    val id: String,
    val name: String,
    val isDefault: Boolean,
    val isAllowedReply: Boolean
)