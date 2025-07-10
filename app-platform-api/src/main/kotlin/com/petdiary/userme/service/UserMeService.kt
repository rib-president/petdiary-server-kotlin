package com.petdiary.userme.service

import com.petdiary.userme.dto.UserMeResponseDto

interface UserMeService {
    fun getMe(): UserMeResponseDto;
}