package com.petdiary.service.impl

import com.petdiary.client.core.dto.SuccessResponseDto
import com.petdiary.domain.entity.File
import com.petdiary.domain.entity.User
import com.petdiary.domain.repository.UserRepository
import com.petdiary.dto.UserMeDetailResponseDto
import com.petdiary.dto.UserMeUpdateRequestDto
import com.petdiary.exception.ApiException
import com.petdiary.exception.enums.ApiExceptionEnum
import com.petdiary.security.SecurityUser
import com.petdiary.service.UserMeService
import com.petdiary.utils.PatchHelper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class UserMeServiceImpl(
    private val userRepository: UserRepository
) : UserMeService {

    override fun getMe(user: SecurityUser): UserMeDetailResponseDto {

        val foundUser = userRepository.findBySystemCode(user.username)
            ?: throw ApiException(ApiExceptionEnum.USER_NOT_FOUND)

        return with(foundUser) {
            UserMeDetailResponseDto(
                displayName = displayName,
                emailAddress = emailAddress,
                avatar = avatar?.run {
                    UserMeDetailResponseDto.Avatar(
                        key = key,
                        location = location,
                        filename = filename,
                        size = size
                    )
                },
                snsType = snsType,
                marketingAgreedDateTime = marketingAgreedDateTime?.toString()
            )
        }
    }

    override fun updateMe(user: SecurityUser, dto: UserMeUpdateRequestDto): SuccessResponseDto {
        val foundUser = userRepository.findBySystemCode(user.username)
            ?: throw ApiException(ApiExceptionEnum.USER_NOT_FOUND)

        val patchHelper = PatchHelper(foundUser)
        patchHelper.patch(dto.displayName, User::displayName)
        dto.marketingAgreedDateTime?.let {patchHelper.patch(it, User::marketingAgreedDateTime)}

        foundUser.avatar = dto.avatar?.map {
            File(
                key = it.key,
                location = it.location,
                filename = it.filename,
                size = it.size
            )
        }
            ?.orElse(null)
            ?: foundUser.avatar

        userRepository.save(foundUser)

        return SuccessResponseDto(success = true)
    }

    override fun withdrawMe(user: SecurityUser): SuccessResponseDto {
        val foundUser = userRepository.findBySystemCode(user.username)
            ?: throw ApiException(ApiExceptionEnum.USER_NOT_FOUND)

        userRepository.delete(foundUser)

        return SuccessResponseDto(success = true)
    }
}