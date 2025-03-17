package ru.test.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.test.domain.models.CourseDomain
import ru.test.domain.repository.DataRepository
import javax.inject.Inject

class GetAllCoursesUseCase @Inject constructor(private val repository: DataRepository) {
   suspend operator fun invoke(): List<CourseDomain> {
        return repository.getAllCourses()
    }
}