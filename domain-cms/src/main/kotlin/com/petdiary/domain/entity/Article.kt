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
@Table(name = "cms__article")
class Article (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val articleId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    @Comment("게시판 FK")
    var board: Board,

    @Column(name = "title", length = 32, nullable = false)
    @Comment("제목")
    var title: String,

    @Column(name = "content", nullable = false)
    @Comment("내용")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @Comment("작성자 FK(user_id)")
    var author: User,

    @Column(name = "creation_date_time", nullable = false)
    @CreationTimestamp
    @Comment("작성일")
    var creationDateTime: LocalDateTime
)