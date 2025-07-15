package com.petdiary.dto

data class FamilyListResponseDto(
    val id: String,
    val name: String,
    val isDefault: Boolean,
    val superAdminName: String,
    val petThumbnailLocation: String
)