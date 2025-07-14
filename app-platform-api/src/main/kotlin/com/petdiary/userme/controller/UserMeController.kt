package com.petdiary.userme.controller

import com.petdiary.client.core.dto.SuccessResponseDto
import com.petdiary.security.SecurityUser
import com.petdiary.userme.dto.UserMeDetailResponseDto
import com.petdiary.userme.dto.UserMeUpdateRequestDto
import com.petdiary.userme.service.UserMeService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping

@RestController
@RequestMapping("/users/me")
class UserMeController(
    private val service: UserMeService
) {
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun getMe(@AuthenticationPrincipal user: SecurityUser): UserMeDetailResponseDto {
        return service.getMe(user)
    }

    @PatchMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun updateMe(@AuthenticationPrincipal user: SecurityUser,
                 @RequestBody @Valid dto: UserMeUpdateRequestDto): SuccessResponseDto {
        return service.updateMe(user, dto)
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.OK)
    fun withdrawMe(@AuthenticationPrincipal user: SecurityUser): SuccessResponseDto {
        return service.withdrawMe(user)
    }
}