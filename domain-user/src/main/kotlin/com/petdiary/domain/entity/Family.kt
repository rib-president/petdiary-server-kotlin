package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user__family")
class Family (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_id")
    val familyId: Long? = null,

    @Column(name = "name", length = 16, nullable = false)
    @Comment("이름")
    var name: String,

    @Column(name = "is_default", nullable = false)
    @ColumnDefault("0")
    @Comment("대표 가족 여부(대표 가족은 삭제 불가)")
    var isDefault: Boolean,

    @Column(name = "is_allowed_reply", nullable = false)
    @ColumnDefault("0")
    @Comment("가족 구성원 사진 댓글 허용 여부")
    var isAllowedReply: Boolean
)