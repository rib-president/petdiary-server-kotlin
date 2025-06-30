package com.petdiary.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(
    private val username: String,
    private val authorities: Collection<GrantedAuthority>
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return this.authorities
    }

    override fun getPassword(): String? {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String? {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}