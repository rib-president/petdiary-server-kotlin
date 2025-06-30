package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user__invitation_link")
class InvitationLink (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_link_id")
    var invitationLinkId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_group_id", nullable = false)
    @Comment("초대하는 가족 그룹 FK")
    val familyGroup: FamilyGroup,

    @Column(name = "authentication_key", length = 16, unique = true)
    @Comment("인증키(링크에 들어가는 키)")
    val authenticationKey: String,

    @Column(name = "creation_date_time", nullable = false)
    @CreationTimestamp
    @Comment("생성일")
    var creationDateTime: LocalDateTime? = null
)