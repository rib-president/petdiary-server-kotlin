package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "user__family_group")
class FamilyGroup (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_group_id")
    val familyGroupId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    @Comment("가족 FK")
    var family: Family,

    @Column(name = "name", length = 16, nullable = false)
    @Comment("그룹명")
    var name: String,

    @Column(name = "is_default", nullable = false)
    @ColumnDefault("0")
    @Comment("기본그룹여부, true인 그룹의 사용자는 1명이고 슈퍼관리자임")
    var isDefault: Boolean
)