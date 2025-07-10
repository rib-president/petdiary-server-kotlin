package com.petdiary.userme.service

import com.petdiary.client.core.dto.SuccessResponseDto
import com.petdiary.security.SecurityUser
import com.petdiary.userme.dto.UserMeDetailResponseDto
import com.petdiary.userme.dto.UserMeUpdateRequestDto

interface UserMeService {
    fun getMe(user: SecurityUser): UserMeDetailResponseDto;

    fun updateMe(user: SecurityUser, dto: UserMeUpdateRequestDto): SuccessResponseDto
}