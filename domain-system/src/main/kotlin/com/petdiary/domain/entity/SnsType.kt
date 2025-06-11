package com.petdiary.domain.entity

import com.petdiary.domain.enums.SnsName
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "system__sns_type")
@DynamicInsert
@DynamicUpdate
class SnsType (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sns_type_id")
    val snsTypeId: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "e_name", unique = true, nullable = false, length = 8)
    @Comment("sns명 ex)NAVER, GOOGLE, KAKAO")
    var name: SnsName
)