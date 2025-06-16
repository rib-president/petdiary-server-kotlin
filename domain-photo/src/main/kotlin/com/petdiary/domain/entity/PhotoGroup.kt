package com.petdiary.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "photo__photo_group",
    indexes = [
        Index(name = "pet_noted_date", columnList = "pet_id, noted_date")
    ])
class PhotoGroup (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val photoGroupId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    @Comment("반려동물 FK")
    var pet: Pet,

    @Column(name = "noted_date", nullable = false)
    @Comment("등록 기준일")
    var notedDate: LocalDate
)