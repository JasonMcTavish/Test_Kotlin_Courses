package ru.test.testkotlincourses.ui.state

sealed class StateLoading() {
    object Default : StateLoading()
    object Loading : StateLoading()
    object Success : StateLoading()
    class Error(private val message: String) : StateLoading()
}