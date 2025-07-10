package com.petdiary.userme.service

import com.petdiary.security.SecurityUser
import com.petdiary.userme.dto.UserMeResponseDto

interface UserMeService {
    fun getMe(user: SecurityUser): UserMeResponseDto;
}