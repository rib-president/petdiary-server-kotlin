package com.petdiary.userme.service.impl

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

    override fun getMe(): UserMeResponseDto {
        val currentUser = SecurityContextHolder.getContext().authentication?.principal as? SecurityUser
            ?: throw RuntimeException("UNAUTHORIZED")

        val user = userMeRepository.findBySystemCode(currentUser.username)
            ?: throw UsernameNotFoundException("User not found")

        return with(user) {
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