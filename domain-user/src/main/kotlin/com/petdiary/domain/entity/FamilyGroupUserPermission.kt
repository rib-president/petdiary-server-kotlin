package com.petdiary.domain.entity

import com.petdiary.domain.entity.embeddedKey.FamilyGroupUserPermissionKey
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user__family_group_user_permission")
class FamilyGroupUserPermission (
    @EmbeddedId
    var familyGroupUserPermissionId: FamilyGroupUserPermissionKey,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("familyGroupUserId")
    @JoinColumn(name = "family_group_user_id", nullable = false)
    @Comment("가족 그룹 사용자 FK")
    var familyGroupUser: FamilyGroupUser,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id",  nullable = false)
    @Comment("권한 FK")
    var permission: Permission
)