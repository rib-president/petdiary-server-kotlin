package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "record__meal")
class Meal (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var mealId: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", nullable = false, unique = true)
    @Comment("기록 FK")
    var record: Record,

    @Column(name = "food_name", nullable = false, length = 16)
    @Comment("먹이 종류 ex)츄르, 사료")
    var foodName: String,

    @Column(name = "measurement", nullable = false, length = 16)
    @Comment("기록 값(gram) ex)3")
    var measurement: String
)