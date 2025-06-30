package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "record__weight")
class Weight (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var weightId: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", nullable = false, unique = true)
    @Comment("기록 FK")
    var record: Record,

    @Column(name = "measurement", nullable = false)
    @Comment("기록 값(gram)")
    var measurement: Integer
)