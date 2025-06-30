package com.petdiary.domain.entity

import com.petdiary.domain.enums.AlarmType
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import java.time.LocalDateTime

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user__alarm_history")
class AlarmHistory (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var alarmHistoryId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("사용자 FK")
    var user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_type", nullable = false, length = 32)
    @Comment("알람 타입")
    val alarmType: AlarmType,

    @Column(name = "title", nullable = false, length = 32)
    @Comment("알림 제목")
    val title: String,

    @Column(name = "description", nullable = false, length = 64)
    @Comment("알림 내용")
    val description: String,

    @Column(name = "params", length = 256)
    @Comment("알람 클릭했을 때 액션 json stringfy값, 첫번째값이 thumbnail image id")
    val params: String,

    @Column(name = "is_read", nullable = false)
    @ColumnDefault("0")
    @Comment("읽음여부, 읽기 API 필요")
    var isRead: Boolean,

    @Column(name = "creation_date_time", nullable = false)
    @CreationTimestamp
    @Comment("생성시간")
    var creationDateTime: LocalDateTime? = null
)