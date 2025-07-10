package com.petdiary.userme.repository

import com.petdiary.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserMeRepository : JpaRepository<User, Long>{
    fun findBySystemCode(systemCode: String): User?
}