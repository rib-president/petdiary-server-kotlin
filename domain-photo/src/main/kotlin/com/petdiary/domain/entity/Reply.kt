package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import java.time.LocalDateTime

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "photo__reply")
class Reply (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var replyId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = false)
    @Comment("사진 FK")
    var photo: Photo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_group_user_id", nullable = false)
    @Comment("가족-사용자 FK")
    var familyGroupUser: FamilyGroupUser,

    @Column(name = "comment", length = 256, nullable = false)
    @Comment("댓글 내용")
    var comment: String,

    @Column(name = "creation_date_time")
    @CreationTimestamp
    @Comment("등록 일시")
    var creationDateTime: LocalDateTime? = null
)