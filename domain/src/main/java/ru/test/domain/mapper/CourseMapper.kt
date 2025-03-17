package ru.test.domain.mapper

import ru.test.domain.models.CourseDomain

interface CourseMapper<T> {
    fun mapToDomain(entity: T): CourseDomain
    fun mapToDomainList(entities: List<T>): List<CourseDomain>
}