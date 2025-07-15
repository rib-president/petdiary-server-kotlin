package com.petdiary.service

import com.petdiary.client.core.dto.ListResponseDto
import com.petdiary.dto.FamilyListResponseDto
import com.petdiary.security.SecurityUser

interface FamilyService {
    fun getMany(user: SecurityUser): ListResponseDto<FamilyListResponseDto>
}