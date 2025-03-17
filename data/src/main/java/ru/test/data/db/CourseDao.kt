package ru.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.test.data.entity.CourseEntity

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses_table")
    fun getAll(): List<CourseEntity>

    @Query("SELECT * FROM courses_table WHERE hasLike = 1")
    fun getFavorite(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses_table where id = :id")
    suspend fun getById(id: Int): CourseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CourseEntity>)

    @Query("UPDATE courses_table SET hasLike = NOT hasLike WHERE id = :id")
    suspend fun changeFavoriteState(id: Int)
}