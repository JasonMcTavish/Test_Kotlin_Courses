package ru.test.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.test.domain.models.CourseDomain
import ru.test.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(private val repository: FavoriteRepository) {
    operator fun invoke(): Flow<List<CourseDomain>> {
        return repository.getFavorites()
    }
}