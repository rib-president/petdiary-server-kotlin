package com.petdiary.userme.controller

import com.petdiary.security.SecurityUser
import com.petdiary.userme.dto.UserMeResponseDto
import com.petdiary.userme.service.UserMeService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    fun getMe(@AuthenticationPrincipal user: SecurityUser): UserMeResponseDto {
        return service.getMe(user)
    }
}