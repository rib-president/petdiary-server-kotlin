package com.petdiary.domain.entity.embeddedKey

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class PhotoFamilyGroupKey(
    @Column(name = "photo_id")
    val photoId: Long,

    @Column(name = "family_group_id")
    val familyGroupId: Long
) : Serializable
