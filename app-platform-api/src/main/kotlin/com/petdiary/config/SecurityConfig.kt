package com.petdiary.config

import com.petdiary.client.core.provider.JwtProvider
import com.petdiary.client.core.redis.RefreshToken
import com.petdiary.client.core.redis.RefreshTokenRepository
import com.petdiary.security.CustomOAuth2User
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtProvider: JwtProvider,
    private val refreshTokenRepository: RefreshTokenRepository) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/", "/favicon.ico", "/v1/auth/login", "/oauth2/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login { oauth ->
                oauth
                    .userInfoEndpoint { userInfo ->
                        userInfo.userService(customOAuth2UserService())
                    }
                    .successHandler(customSuccessHandler())
            }

        return http.build()
    }

    @Bean
    fun customOAuth2UserService(): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
        return OAuth2UserService<OAuth2UserRequest, OAuth2User> { userRequest ->
            val delegate = DefaultOAuth2UserService()
            val oAuth2User = delegate.loadUser(userRequest)

            // 사용자 정보 가공
            val attributes = oAuth2User.attributes
            val email = (attributes["kakao_account"] as? Map<*, *>)?.get("email") as? String?: ""

            // TODO: 사용자 등록 또는 조회 로직
            // val user = userRepository.findOrCreate(email)
            // val userId = user.userId
            val userId = ""

            CustomOAuth2User(oAuth2User, email, userId)
        }

    }

    @Bean
    fun customSuccessHandler(): AuthenticationSuccessHandler {
        return AuthenticationSuccessHandler { request, response, authentication ->
            val oAuth2User = authentication.principal as? CustomOAuth2User
                ?: throw IllegalStateException("USER NOT FOUND")

            val userId = oAuth2User.userId

            val accessToken = jwtProvider.getAccessToken(userId)
            val refreshToken = jwtProvider.getRefreshToken(userId)

            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            val responseBody = """{
                "accessToken": "$accessToken",
                "refreshToken": "$refreshToken"
            }"""

            response.writer.write(responseBody)
            response.writer.flush()

            // TODO: 리프레시 토큰 보관 로직 재정비
            refreshTokenRepository.save(RefreshToken(
                userId = userId,
                token = refreshToken,
                expiry = System.currentTimeMillis(),
                ttl = System.currentTimeMillis()
            ))
        }
    }
}