package ru.test.domain.usecase

import ru.test.domain.repository.FavoriteRepository
import javax.inject.Inject

class ChangeFavoriteUseCase @Inject constructor(private val repository: FavoriteRepository) {
    suspend operator fun invoke(id: Int) {
        repository.changeFavoriteState(id)
    }
}