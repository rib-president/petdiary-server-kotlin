package com.petdiary.controller

import com.petdiary.client.core.dto.ListResponseDto
import com.petdiary.dto.FamilyDetailResponseDto
import com.petdiary.dto.FamilyListResponseDto
import com.petdiary.service.FamilyService
import com.petdiary.security.SecurityUser
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/families")
class FamilyController(
    private val service: FamilyService
) {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun getMany(@AuthenticationPrincipal user: SecurityUser): ListResponseDto<FamilyListResponseDto> {
        return service.getMany(user);
    }

    @GetMapping("/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    fun getOne(@AuthenticationPrincipal user: SecurityUser, familyId: Long): FamilyDetailResponseDto {
        return service.getOne(user, familyId)
    }
}