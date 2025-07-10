package com.petdiary.userme.controller

import com.petdiary.userme.dto.UserMeResponseDto
import com.petdiary.userme.service.UserMeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/me")
class UserMeController(
    private val service: UserMeService
) {
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun getMe(): UserMeResponseDto {
        return service.getMe()
    }
}