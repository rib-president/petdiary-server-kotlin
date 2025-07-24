package com.petdiary.repository.impl

import com.petdiary.domain.entity.Permission
import com.petdiary.domain.entity.QFamily
import com.petdiary.domain.entity.QFamilyGroup
import com.petdiary.domain.entity.QFamilyGroupUser
import com.petdiary.domain.entity.QFamilyGroupUserPermission
import com.petdiary.domain.entity.QPermission
import com.petdiary.domain.entity.QPet
import com.petdiary.domain.entity.QUser
import com.petdiary.domain.entity.User
import com.petdiary.dto.FamilyListResponseDto
import com.petdiary.repository.FamilyQueryRepository
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory

class FamilyQueryRepositoryImpl(private val queryFactory: JPAQueryFactory): FamilyQueryRepository {
    override fun findAllFamilyByUserId(user: User): List<FamilyListResponseDto> {
        val query = queryFactory.select(
            Projections.constructor(
                FamilyListResponseDto::class.java,
                Expressions.stringTemplate("{0}", QFamily.family.familyId),
                QFamily.family.name,
                QFamily.family.isDefault,
                JPAExpressions.select(QUser("superAdmin").displayName)
                    .from(QFamily("subFamily"))
                    .innerJoin(QFamilyGroup.familyGroup)
                    .on(QFamilyGroup.familyGroup.family.eq(QFamily("subFamily")),
                        QFamilyGroup.familyGroup.isDefault.eq(true))
                    .innerJoin(QFamilyGroupUser.familyGroupUser)
                    .on(QFamilyGroupUser.familyGroupUser.familyGroup.eq(QFamilyGroup.familyGroup))
                    .innerJoin(QUser("superAdmin"))
                    .on(QUser("superAdmin").eq(QFamilyGroupUser.familyGroupUser.user))
                    .where(QFamilyGroup.familyGroup.family.eq(QFamily.family)),
                QPet.pet.thumbnail.location
            )
        ).from(QFamilyGroupUser.familyGroupUser)
            .innerJoin(QFamilyGroup.familyGroup)
            .on(QFamilyGroup.familyGroup.eq(QFamilyGroupUser.familyGroupUser.familyGroup))
            .innerJoin(QFamily.family)
            .on(QFamily.family.eq(QFamilyGroup.familyGroup.family))
            .innerJoin(QPet.pet)
            .on(QPet.pet.family.eq(QFamily.family))
            .where(QFamilyGroupUser.familyGroupUser.user.eq(user))

        return query.fetch();
    }
}