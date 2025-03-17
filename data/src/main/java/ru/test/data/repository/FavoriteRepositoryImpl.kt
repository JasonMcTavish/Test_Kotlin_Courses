package ru.test.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.test.data.db.CourseDao
import ru.test.data.entity.CourseEntity
import ru.test.domain.mapper.CourseMapper
import ru.test.domain.models.CourseDomain
import ru.test.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao,
    private val courseMapper: CourseMapper<CourseEntity>
) : FavoriteRepository {
    override fun getFavorites(): Flow<List<CourseDomain>> {
        return courseDao.getFavorite().map { entities ->
            courseMapper.mapToDomainList(entities)
        }
    }

    override suspend fun changeFavoriteState(id: Int) {
        try {
            courseDao.changeFavoriteState(id)
        } catch (e: Exception) {
            throw Exception()
        }
    }

}