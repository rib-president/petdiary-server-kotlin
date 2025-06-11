package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user__user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val userId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sns_type_id", nullable = false)
    @Comment("sns type FK")
    var snsType: SnsType,

    @Column(name = "sns_user_id", length = 64, nullable = false, unique = true)
    @Comment("sns에서 발급한 사용자 고유키")
    var snsUserId: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    @Comment("아바타 FK")
    var avatar: File,

    @Column(name = "marketing_agreed_date_time")
    @Comment("마케팅 정보 수신 동의 시간")
    var marketingAgreedDateTime: LocalDateTime,

    @Column(name = "display_name", length = 16, nullable = false)
    @Comment("사용자명")
    var displayName: String,

    @Column(name = "email_address", length = 64)
    @Comment("이메일주소")
    var emailAddress: String,

    @Column(name = "creation_date_time", nullable = false)
    @CreationTimestamp
    @Comment("등록일")
    var creationDateTime: LocalDateTime,

    @Column(name = "updated_date_time", nullable = false)
    @ColumnDefault("NOW(6)")
    @UpdateTimestamp
    @Comment("수정일")
    var updatedDateTime: LocalDateTime
)