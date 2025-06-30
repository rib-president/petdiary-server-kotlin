package com.petdiary.domain.entity

import com.petdiary.domain.enums.PetType
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "pet__breed_type",
    indexes = [
        Index(name = "pet_type_name", columnList = "pet_type, name")
    ])
class BreedType (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var breedTypeId: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type", nullable = false, length = 8)
    @Comment("반려동물 타입")
    val petType: PetType,

    @Column(name = "name", length = 16, nullable = false)
    @Comment("품종명 ex)코리안숏헤어, 시바")
    var name: String
)