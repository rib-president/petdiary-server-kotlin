package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "record__stroll")
class Stroll (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val strollId: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", nullable = false, unique = true)
    @Comment("기록 FK")
    var record: Record,

    @Column(name = "location", length = 16)
    @Comment("산책 장소")
    var location: String,

    @Column(name = "end_date_time", nullable = false)
    @Comment("종료시간")
    var endDateTime: LocalDateTime
)