package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "cms__banner_type",
    indexes = [
        Index(name = "page_location", columnList = "page, location")
    ])
class BannerType (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bannerTypeId: Long? = null,

    @Column(name = "page", length = 32, nullable = false)
    @Comment("게시할 페이지(MAIN, MYPAGE)")
    var page: String,

    @Column(name = "location", length = 16, nullable = false)
    @Comment("게시될 위치(TOP, MID, BOT)")
    var location: String,

    @Column(name = "width", nullable = false)
    @Comment("가로")
    var width: Integer,

    @Column(name = "height", nullable = false)
    @Comment("높이")
    var height: Integer
)