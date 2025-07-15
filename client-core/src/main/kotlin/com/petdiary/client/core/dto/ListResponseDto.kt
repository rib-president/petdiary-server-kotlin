package com.petdiary.client.core.dto

data class ListResponseDto <T>(
    val total: Long,
    val count: Int,
    val limit: Int,
    val offset: Int,
    val items: List<T>
)