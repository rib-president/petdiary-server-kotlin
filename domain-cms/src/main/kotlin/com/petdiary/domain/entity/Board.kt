package com.petdiary.domain.entity

import com.petdiary.domain.enums.BoardType
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "cms__board")
class Board (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val boardId: Long? = null,

    @Column(name = "type", nullable = false, unique = true)
    @Comment("게시판 타입")
    @Enumerated(EnumType.STRING)
    var type: BoardType
)