package com.petdiary.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
class SecurityConfig {

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
            val email = (attributes["kakao_account"] as Map<*, *>)["email"] as String?

            // TODO: 사용자 등록 또는 조회 로직
            // val user = userRepository.findOrCreate(email)

            oAuth2User
        }

    }

    @Bean
    fun customSuccessHandler(): AuthenticationSuccessHandler {
        return AuthenticationSuccessHandler { request, response, authentication -> }
    }
}