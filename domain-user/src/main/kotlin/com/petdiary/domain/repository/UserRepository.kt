package com.petdiary.domain.repository

import com.petdiary.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findBySystemCode(systemCode: String): User?
}