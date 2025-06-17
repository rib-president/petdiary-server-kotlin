package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "record__defecation")
class Defecation (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val defecationId: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", nullable = false, unique = true)
    @Comment("기록 FK")
    var record: Record,

    @Column(name = "is_poop", nullable = false)
    @ColumnDefault("0")
    @Comment("응가인지 여부")
    var isPoop: Boolean,

    @Column(name = "is_pee", nullable = false)
    @ColumnDefault("0")
    @Comment("쉬야인지 여부")
    var isPee: Boolean
)