package ru.test.testkotlincourses.ui.course

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.test.domain.models.CourseDomain
import ru.test.domain.usecase.ChangeFavoriteUseCase
import ru.test.domain.usecase.GetCourseByIdUseCase
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val getCourseByIdUseCase: GetCourseByIdUseCase,
    private val changeFavoriteUseCase: ChangeFavoriteUseCase
) : ViewModel() {

    private val _course = MutableStateFlow<CourseDomain?>(null)
    val course = _course.asStateFlow()

    fun changeFavoriteStateById(id: Int) {
        viewModelScope.launch {
            try {
                changeFavoriteUseCase(id)
                Log.d("viewModel", "changeFavoriteStateById: $id")
            } catch (_: Exception) {

            }
        }
    }

    fun getCourseById(id: Int) {
        viewModelScope.launch {
            try {
                _course.update { getCourseByIdUseCase(id) }
            } catch (e: Exception) {
                Log.d("course", "getCourseById: ${course.value}, ${e.message} ")
            }
        }
    }
}