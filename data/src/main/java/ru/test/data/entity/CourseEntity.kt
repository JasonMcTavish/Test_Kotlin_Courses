package ru.test.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.test.data.model.Course


@Entity(tableName = "courses_table")
class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val rating: String,
    val startDate: String,
    val price: String,
    val hasLike: Boolean,
    val publishDate: String
) {
    fun toDto() = Course(
        id, title, text, rating.toDouble(), startDate, price, hasLike, publishDate
    )

    companion object {
        fun fromDto(dto: Course) = with(dto) {
            CourseEntity(
                id, title, text, rate.toString(), startDate, price, hasLike, publishDate
            )
        }
    }
}
