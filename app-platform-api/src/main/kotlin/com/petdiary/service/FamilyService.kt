package com.petdiary.service

import com.petdiary.client.core.dto.ListResponseDto
import com.petdiary.dto.FamilyDetailResponseDto
import com.petdiary.dto.FamilyListResponseDto
import com.petdiary.security.SecurityUser

interface FamilyService {
    fun getMany(user: SecurityUser): ListResponseDto<FamilyListResponseDto>
    fun getOne(user: SecurityUser, familyId: Long): FamilyDetailResponseDto
}