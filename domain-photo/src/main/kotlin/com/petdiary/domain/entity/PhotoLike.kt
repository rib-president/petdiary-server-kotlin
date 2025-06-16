package com.petdiary.domain.entity

import com.petdiary.domain.entity.embeddedKey.PhotoFamilyGroupUserKey
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "photo__photo_like")
class PhotoLike (
    @EmbeddedId
    var photoLikeId: PhotoFamilyGroupUserKey,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("photoId")
    @JoinColumn(name = "photo_id", nullable = false)
    @Comment("사진 PK")
    var photo: Photo,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("familyGroupUserId")
    @JoinColumn(name = "family_group_user_id", nullable = false)
    @Comment("가족-사용자 PK")
    var familyGroupUser: FamilyGroupUser
)