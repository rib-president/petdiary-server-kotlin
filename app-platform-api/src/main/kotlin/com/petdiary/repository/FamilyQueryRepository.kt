package com.petdiary.repository

import com.petdiary.domain.entity.User
import com.petdiary.dto.FamilyListResponseDto
import org.springframework.stereotype.Repository

@Repository
interface FamilyQueryRepository {
    fun findAllFamilyByUserId(user: User): List<FamilyListResponseDto>
}