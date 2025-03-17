package ru.test.data.mapper

import ru.test.data.entity.CourseEntity
import ru.test.domain.mapper.CourseMapper
import ru.test.domain.models.CourseDomain
import javax.inject.Inject

class CourseMapperImpl @Inject constructor() : CourseMapper<CourseEntity> {
    override fun mapToDomain(entity: CourseEntity): CourseDomain {
        return CourseDomain(
            id = entity.id,
            title = entity.title,
            text = entity.text,
            rating = entity.rating.toString(),
            startDate = entity.startDate,
            price = entity.price,
            hasLike = entity.hasLike,
            publishDate = entity.publishDate
        )
    }

    override fun mapToDomainList(entities: List<CourseEntity>): List<CourseDomain> {
        return entities.map { mapToDomain(it) }
    }

}