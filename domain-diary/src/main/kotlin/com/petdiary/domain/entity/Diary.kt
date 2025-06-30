package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "diary__diary",
    indexes = [
        Index(name = "pet_noted_date", columnList = "pet_id, noted_date")
    ])
class Diary (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var diaryId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    @Comment("반려동물 FK")
    var pet: Pet,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @Comment("작성자(양육자의 family_group_user_id), 양육자가 양도되었을 경우에 대비")
    var author: FamilyGroupUser,

    @Column(name = "noted_date", nullable = false)
    @Comment("등록 기준일")
    var notedDate: LocalDate,

    @Column(name = "content", nullable = false, length = 256)
    @Comment("일기 내용")
    var content: String,

    @Column(name = "is_private", nullable = false)
    @ColumnDefault("0")
    @Comment("공유/비공개 여부")
    var isPrivate: Boolean,

    @Column(name = "emotion_level")
    @Comment("기분 정도, 1-나쁨 ~ 5-좋음")
    var emotionLevel: Integer
)