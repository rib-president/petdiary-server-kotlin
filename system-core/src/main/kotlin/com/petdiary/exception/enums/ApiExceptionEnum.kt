package com.petdiary.exception.enums

import org.springframework.http.HttpStatus

enum class ApiExceptionEnum(
    val code: String,
    val message: String,
    val status: HttpStatus
) {
    // 401
    BAD_REQUEST("E40100000", "bad request", HttpStatus.BAD_REQUEST),

    // 404
    NOT_FOUND("E40400000", "not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("E40401001", "user not found", HttpStatus.NOT_FOUND)

}