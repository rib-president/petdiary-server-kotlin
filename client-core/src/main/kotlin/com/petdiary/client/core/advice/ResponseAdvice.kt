package com.petdiary.client.core.advice

import com.petdiary.client.core.dto.ResponseDto
import jakarta.servlet.ServletResponse
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
class ResponseAdvice : ResponseBodyAdvice<Any> {
    override fun supports(
        returnType: MethodParameter,
        converterType: Class<out HttpMessageConverter<*>?>
    ) = true

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>?>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): ResponseDto<*> {
        val code = (response as? org.springframework.http.server.ServletServerHttpResponse)
            ?.servletResponse
            ?.status ?: HttpStatus.OK.value()

        return when(body) {
            is ResponseDto<*> -> body
            is ProblemDetail -> ResponseDto<Nothing>(
                code = body.status.toString(),
                status = body.properties?.get("exception")?.toString()?: "UNKNOWN_ERROR",
                error = body
            )
            else -> ResponseDto(
                code = code.toString(),
                status = HttpStatus.resolve(code)?.reasonPhrase ?: "OK",
                data = body
            )
        }
    }
}