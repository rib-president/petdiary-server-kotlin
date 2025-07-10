package com.petdiary.userme.service.impl

import com.petdiary.exception.ApiException
import com.petdiary.exception.enums.ApiExceptionEnum
import com.petdiary.security.SecurityUser
import com.petdiary.userme.dto.UserMeResponseDto
import com.petdiary.userme.repository.UserMeRepository
import com.petdiary.userme.service.UserMeService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class UserMeServiceImpl(
    private val userMeRepository: UserMeRepository
) : UserMeService {

    override fun getMe(user: SecurityUser): UserMeResponseDto {

        val foundUser = userMeRepository.findBySystemCode(user.username)
            ?: throw ApiException(ApiExceptionEnum.USER_NOT_FOUND)

        return with(foundUser) {
            UserMeResponseDto(
                displayName = displayName,
                emailAddress = emailAddress,
                avatar = avatar?.run {
                    UserMeResponseDto.Avatar(
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
}