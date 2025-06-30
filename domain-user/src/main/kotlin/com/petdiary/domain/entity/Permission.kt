package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "user__permission")
class Permission (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    var permissionId: Long? = null,

    @Column(name = "e_name", nullable = false, length = 32, unique = true)
    @Comment("권한명")
    val name: String
)