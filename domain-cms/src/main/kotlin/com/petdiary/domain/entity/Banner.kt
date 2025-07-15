package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "cms__banner", indexes = [
    Index(name = "banner_type_id_order", columnList = "banner_type_id, `order`")
])
class Banner (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bannerId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_type_id", nullable = false)
    @Comment("배너 타입 FK")
    var bannerType: BannerType,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = false)
    @Comment("이미지 FK")
    var image: File,

    @Column(name = "`order`", nullable = false)
    @Comment("배치 순서")
    var order: Int,

    @Column(name = "link", length = 128)
    @Comment("연결된 링크")
    var link: String,

    @Column(name = "post_start_date_time", nullable = false)
    @Comment("게시 시작 시간")
    var postStartDateTime: LocalDateTime,

    @Column(name = "post_end_date_time", nullable = false)
    @Comment("게시 종료 시간")
    var postEndDateTime: LocalDateTime
)