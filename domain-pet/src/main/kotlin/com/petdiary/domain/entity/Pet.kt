package com.petdiary.domain.entity

import com.petdiary.domain.enums.Gender
import com.petdiary.domain.enums.PetType
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "pet__pet")
class Pet (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val petId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    @Comment("가족 FK")
    var family: Family,

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type", nullable = false, length = 8)
    @Comment("반려동물 타입")
    var petType: PetType,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 16)
    @Comment("성별")
    var gender: Gender,

    @Column(name = "breed_name", nullable = false, length = 16)
    @Comment("denormalize된 품종 타입명")
    var breedName: String,

    @Column(name = "name", nullable = false, length = 32)
    @Comment("이름")
    var name: String,

    @Column(name = "birth_date", nullable = false)
    @Comment("생일")
    var birthDate: LocalDate,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbnail_id")
    @Comment("썸네일 FK")
    var thumbnail: File
)