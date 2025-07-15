package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "photo__photo",
    indexes = [
        Index(name = "photo_group_file", columnList = "photo_group_id, file_id")
    ])
class Photo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var photoId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_group_id", nullable = false)
    @Comment("사진 그룹 FK")
    var photoGroup: PhotoGroup,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_group_user_id", nullable = false)
    @Comment("가족-사용자 FK(업로드한 사용자)")
    var familyGroupUser: FamilyGroupUser,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    @Comment("파일 FK")
    var file: File,

    @Column(name = "keyword", length = 64)
    @Comment("키워드(,로 연결)")
    var keyword: String,

    @Column(name = "description", length = 128)
    @Comment("사진 description")
    var description: String,

    @Column(name = "read_count", nullable = false)
    @ColumnDefault("0")
    @Comment("조회수")
    var readCount: Int = 0,

    @Column(name = "is_default", nullable = false)
    @ColumnDefault("0")
    @Comment("대표사진 여부")
    var isDefault: Boolean,

    @Column(name = "creation_date_time", nullable = false)
    @CreationTimestamp
    @Comment("등록일")
    var creationDateTime: LocalDateTime? = null
)