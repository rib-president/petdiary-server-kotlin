package com.petdiary.client.core.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.petdiary.client.core.dto.ResponseDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

class CustomAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.FORBIDDEN,
            accessDeniedException.message ?: "ACCESS_DENIED"
        )

        problem.title = "Access Denied"
        problem.setProperty("exception", accessDeniedException.javaClass.simpleName)

        val responseDto = ResponseDto<Nothing>(
            code = problem.status.toString(),
            status = accessDeniedException.javaClass.simpleName,
            error = problem
        )

        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = "application/problem+json"
        response.characterEncoding = "UTF-8"
        response.writer.write(objectMapper.writeValueAsString(responseDto))
    }
}