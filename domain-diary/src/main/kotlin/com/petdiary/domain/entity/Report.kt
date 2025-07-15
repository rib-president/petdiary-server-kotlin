package com.petdiary.domain.entity

import com.petdiary.domain.enums.ReportType
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "diary__report")
class Report (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var reportId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("사용자 FK")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", nullable = false)
    @Comment("일기 FK")
    var diary: Diary,

    @Column(name = "report_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("신고타입")
    val reportType: ReportType
)