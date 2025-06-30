package com.petdiary.client.core.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.petdiary.client.core.dto.ResponseDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.UNAUTHORIZED,
            authException.message ?: "UNAUTHORIZED"
        )

        problem.title = "Unauthorized"
        problem.setProperty("exception", authException.javaClass.simpleName)

        val responseDto = ResponseDto<Nothing>(
            code = problem.status.toString(),
            status = authException.javaClass.simpleName,
            error = problem
        )

        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/problem+json"
        response.characterEncoding = "UTF-8"
        response.writer.write(objectMapper.writeValueAsString(responseDto))
    }
}