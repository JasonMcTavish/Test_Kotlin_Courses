package ru.test.domain.usecase

import ru.test.domain.models.CourseDomain
import ru.test.domain.repository.DataRepository
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(private val repository: DataRepository) {
    suspend operator fun invoke(id: Int): CourseDomain {
        return repository.getCourseById(id)
    }
}