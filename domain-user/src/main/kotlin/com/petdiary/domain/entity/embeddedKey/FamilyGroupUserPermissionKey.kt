package com.petdiary.domain.entity.embeddedKey

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class FamilyGroupUserPermissionKey(
    @Column(name = "family_group_user_id")
    val familyGroupUserId: Long,

    @Column(name = "permission_id")
    val permissionId: Long
) : Serializable
