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
    val fileId: Long? = null,

    @Column(name = "`location`", length = 1024, nullable = false)
    @Comment("파일 저장 위치")
    var location: String,

    @Column(name = "filename", length = 128, nullable = false)
    @Comment("파일명")
    var filename: String,

    @Column(name = "`key`", length = 128, nullable = false)
    @Comment("파일 식별키")
    var key: String
)