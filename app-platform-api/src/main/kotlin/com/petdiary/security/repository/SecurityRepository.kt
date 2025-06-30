package com.petdiary.security.repository

import com.petdiary.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface SecurityRepository : JpaRepository<User, Long> {
    fun findBySystemCode(systemCode: String): User?

    fun findByEmailAddress(emailAddress: String): User?
}