package com.petdiary.client.core.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(Exception::class)
    fun handleAll(ex: Exception): ProblemDetail {
        val problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ex.message ?: "서버 오류 발생"
        )
        problem.title = "Internal Server Error"
        problem.setProperty("exception", ex.javaClass.simpleName)
        return problem
    }
}