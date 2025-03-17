package ru.test.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.test.domain.models.CourseDomain

interface FavoriteRepository {
    fun getFavorites(): Flow<List<CourseDomain>>

    suspend fun changeFavoriteState(id: Int)
}