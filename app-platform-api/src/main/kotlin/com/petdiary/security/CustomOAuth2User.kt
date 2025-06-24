package com.petdiary.security

import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2User(
    private val oAuth2User: OAuth2User,
    val email: String,
    val userId: String
): OAuth2User by oAuth2User {
    fun getEmail(): String {
        return email
    }

    fun getUserId(): String {
        return userId
    }

    override fun <A : Any?> getAttribute(name: String?): A? {
        return oAuth2User.getAttribute(name)
    }
}