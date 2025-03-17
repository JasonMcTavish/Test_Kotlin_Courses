package ru.test.domain.repository

import ru.test.domain.models.CourseDomain

interface DataRepository {
    suspend fun getAllCourses(): List<CourseDomain>

    suspend fun getCourseById(id: Int): CourseDomain

    suspend fun getDataFromApi()
}