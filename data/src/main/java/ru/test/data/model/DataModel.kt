package ru.test.data.model

data class DataModel(
    val courses: List<Course>
)

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val rate: Double,
    val startDate: String,
    val price: String,
    val hasLike: Boolean,
    val publishDate: String
)