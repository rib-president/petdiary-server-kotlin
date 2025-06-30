package com.petdiary.security.service

import com.petdiary.security.SecurityUser
import com.petdiary.security.repository.SecurityRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SecurityService(
    private val securityRepository: SecurityRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = securityRepository.findBySystemCode(username)
            ?: throw UsernameNotFoundException("$username NOT FOUND")

        return SecurityUser(
            username = user.systemCode,
            authorities = mutableListOf()
        )
    }
}