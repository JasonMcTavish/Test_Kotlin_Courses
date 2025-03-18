package ru.test.testkotlincourses.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.test.domain.models.CourseDomain
import ru.test.domain.usecase.ChangeFavoriteUseCase
import ru.test.domain.usecase.GetAllCoursesUseCase
import ru.test.domain.usecase.GetDataFromApiUseCase
import ru.test.domain.usecase.GetFavoriteUseCase
import ru.test.testkotlincourses.ui.state.StateLoading
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val getDataFromApiUseCase: GetDataFromApiUseCase,
    private val changeFavoriteUseCase: ChangeFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<StateLoading>(StateLoading.Default)
    val state = _state.asStateFlow()
    private val _courses = MutableLiveData<List<CourseDomain>>()
    val courses: LiveData<List<CourseDomain>> = _courses

    val favorites: LiveData<List<CourseDomain>> = getFavoriteUseCase().asLiveData()

    private var isAscendingOrder = true

    init {
        getData()
    }

    fun getData() {
        _state.value = StateLoading.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getDataFromApiUseCase()
                val list: List<CourseDomain> = getAllCoursesUseCase()
                if (!list.isEmpty()) {
                    _courses.postValue(list)
                    _state.value = StateLoading.Success
                } else {
                    _state.value = StateLoading.Error("Ошибка загрузки")
                }
            } catch (e: Exception) {
                _state.value = StateLoading.Error(e.message.toString())
            }


        }
    }

    fun changeFavoriteStateById(id: Int) {
        viewModelScope.launch {
            try {
                changeFavoriteUseCase(id)
                Log.d("viewModel", "changeFavoriteStateById: $id")
            } catch (_: Exception) {

            }
        }
    }

    fun toggleSortOrder() {
        isAscendingOrder = !isAscendingOrder
        sortCourses()
    }

    private fun sortCourses() {
        val currentList = _courses.value ?: return
        val sortedList = if (isAscendingOrder) {
            currentList.sortedBy { it.publishDate }
        } else {
            currentList.sortedByDescending { it.publishDate }
        }
        _courses.value = sortedList
    }

    fun getSortText(): String {
        return if (isAscendingOrder) {
            "По возрастанию даты"
        } else {
            "По убыванию даты"
        }
    }
}