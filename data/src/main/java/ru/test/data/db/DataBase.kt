package ru.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.test.data.entity.CourseEntity


@Database(
    entities = [CourseEntity::class], version = 1, exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}