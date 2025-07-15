package com.petdiary.service

import com.petdiary.client.core.dto.SuccessResponseDto
import com.petdiary.dto.UserMeDetailResponseDto
import com.petdiary.dto.UserMeUpdateRequestDto
import com.petdiary.security.SecurityUser

interface UserMeService {
    fun getMe(user: SecurityUser): UserMeDetailResponseDto;

    fun updateMe(user: SecurityUser, dto: UserMeUpdateRequestDto): SuccessResponseDto

    fun withdrawMe(user: SecurityUser): SuccessResponseDto
}