package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "user__family_group_user",
    indexes = [
        Index(name = "family_group_user", columnList = "family_group_id, user_id")
    ])
class FamilyGroupUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_group_user_id")
    var familyGroupUserId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_group_id", nullable = false)
    @Comment("가족 그룹 FK")
    var familyGroup: FamilyGroup,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("사용자 FK")
    var user: User,

    @Column(name = "name", length = 16, nullable = false)
    @Comment("가족내 이름")
    var name: String,

    @Column(name = "photo_upload_alarm_agreed_date_time")
    @Comment("사진 업로드 알림 허용 일시")
    var photoUploadAlarmAgreedDateTime: LocalDateTime? = null,

    @Column(name = "photo_reply_alarm_agreed_date_time")
    @Comment("사진 댓글 알림 허용 일시")
    var photoReplyAlarmAgreedDateTime: LocalDateTime? = null,

    @Column(name = "alarm_start_hour")
    @Comment("지정된 알림 시작 시간 0~24")
    var alarmStartHour: Integer? = null,

    @Column(name = "alarm_end_hour")
    @Comment("지정된 알림 끝 시간 0~24")
    var alarmEndHour: Integer? = null
)