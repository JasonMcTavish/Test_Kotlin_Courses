package ru.test.domain.usecase

import ru.test.domain.repository.DataRepository
import javax.inject.Inject

class GetDataFromApiUseCase @Inject constructor(private val repository: DataRepository) {
    suspend operator fun invoke() {
        repository.getDataFromApi()
    }
}