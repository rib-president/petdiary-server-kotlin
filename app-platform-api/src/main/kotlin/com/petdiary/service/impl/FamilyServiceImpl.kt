package com.petdiary.service.impl

import com.petdiary.client.core.dto.ListResponseDto
import com.petdiary.domain.repository.UserRepository
import com.petdiary.dto.FamilyDetailResponseDto
import com.petdiary.dto.FamilyListResponseDto
import com.petdiary.exception.ApiException
import com.petdiary.exception.enums.ApiExceptionEnum
import com.petdiary.repository.FamilyQueryRepository
import com.petdiary.security.SecurityUser
import com.petdiary.service.FamilyService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class FamilyServiceImpl(
    private val userRepository: UserRepository,
    private val familyQueryRepository: FamilyQueryRepository
): FamilyService {

    override fun getMany(user: SecurityUser): ListResponseDto<FamilyListResponseDto> {
        val foundUser = userRepository.findBySystemCode(user.username)
            ?: throw ApiException(ApiExceptionEnum.USER_NOT_FOUND);

        val items = familyQueryRepository.findAllFamilyByUserId(foundUser)

        return ListResponseDto(
            total = items.size.toLong(),
            count = items.size,
            offset = 0,
            limit = items.size,
            items = items
        )
    }

    override fun getOne(user: SecurityUser,
                        familyId: Long): FamilyDetailResponseDto {
        val foundUser = userRepository.findBySystemCode(user.username)
            ?: throw ApiException(ApiExceptionEnum.USER_NOT_FOUND)

        val family = foundUser.familyGroupUsers
            .find { it.familyGroup.family.familyId == familyId }?.familyGroup?.family
            ?: throw ApiException(ApiExceptionEnum.BAD_REQUEST)

        return FamilyDetailResponseDto(
            id = family.familyId.toString(),
            name = family.name,
            isDefault = family.isDefault,
            isAllowedReply = family.isAllowedReply
        )
    }
}