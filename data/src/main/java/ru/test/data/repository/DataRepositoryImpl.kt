package ru.test.data.repository

import ru.test.data.api.ApiService
import ru.test.data.db.CourseDao
import ru.test.data.entity.CourseEntity
import ru.test.domain.mapper.CourseMapper
import ru.test.domain.models.CourseDomain
import ru.test.domain.repository.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val courseDao: CourseDao,
    private val courseMapper: CourseMapper<CourseEntity>
) : DataRepository {
    override suspend fun getAllCourses(): List<CourseDomain> {
        return courseMapper.mapToDomainList(courseDao.getAll())

    }

    override suspend fun getCourseById(id: Int): CourseDomain {
        return courseMapper.mapToDomain(courseDao.getById(id))
    }

    override suspend fun getDataFromApi() {
        try {
            val response = apiService.getData()

            if (!response.isSuccessful) {

                throw Exception()
            }
            val body = response.body() ?: throw Exception()
            val coursesList = body.courses

            courseDao.insertAll((coursesList.map { CourseEntity.fromDto(it) }))
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e)
        }
    }

}