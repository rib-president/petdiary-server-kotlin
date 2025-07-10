package com.petdiary.exception.enums

import org.springframework.http.HttpStatus

enum class ApiExceptionEnum(
    val code: String,
    val message: String,
    val status: HttpStatus
) {
    // 404
    NOT_FOUND("E40400000", "not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("E40401001", "user not found", HttpStatus.NOT_FOUND)

}