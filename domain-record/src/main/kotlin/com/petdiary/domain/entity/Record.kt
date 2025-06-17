package com.petdiary.domain.entity

import com.petdiary.domain.enums.RecordCategory
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "record__record")
class Record (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val recordId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    @Comment("반려동물 FK")
    var pet: Pet,

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    @Comment("기록 카테고리")
    var category: RecordCategory,

    @Column(name = "recorded_date_time", nullable = false)
    @Comment("기록 시간")
    var recordedDateTime: LocalDateTime,

    @Column(name = "memo", length = 128)
    @Comment("메모")
    var memo: String
)