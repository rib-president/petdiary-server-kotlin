package com.petdiary.domain.entity

import com.petdiary.domain.entity.embeddedKey.PhotoFamilyGroupKey
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "photo__photo_family_group")
class PhotoFamilyGroup (
    @EmbeddedId
    var photoFamilyGroupId: PhotoFamilyGroupKey,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = false)
    @Comment("사진 PK")
    @MapsId("photoId")
    var photo: Photo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_group_id", nullable = false)
    @Comment("가족 그룹 PK")
    @MapsId("familyGroupId")
    var familyGroup: FamilyGroup
)