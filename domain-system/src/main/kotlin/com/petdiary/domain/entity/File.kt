package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "system__file")
class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    var fileId: Long? = null,

    @Column(name = "`location`", length = 1024, nullable = false)
    @Comment("파일 저장 위치")
    val location: String,

    @Column(name = "filename", length = 128, nullable = false)
    @Comment("파일명")
    val filename: String,

    @Column(name = "`key`", length = 128, nullable = false)
    @Comment("파일 식별키")
    val key: String
)