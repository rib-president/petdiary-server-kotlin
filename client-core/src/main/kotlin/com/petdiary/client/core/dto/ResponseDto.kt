package com.petdiary.client.core.dto

import org.springframework.http.ProblemDetail

data class ResponseDto<T> (
    val code: String,
    val status: String,
    val data: T? = null,
    val error: ProblemDetail? = null
)