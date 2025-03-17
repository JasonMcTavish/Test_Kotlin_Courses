package ru.test.domain.models

data class CourseDomain(
    val id: Int,
    val title: String,
    val text: String,
    val rating: String,
    val startDate: String,
    val price: String,
    val hasLike: Boolean,
    val publishDate: String
)
