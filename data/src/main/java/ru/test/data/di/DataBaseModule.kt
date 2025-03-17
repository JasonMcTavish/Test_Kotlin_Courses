package ru.test.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.test.data.db.CourseDao
import ru.test.data.db.DataBase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun providesDataBase(
        @ApplicationContext
        context: Context
    ): DataBase = Room.databaseBuilder(context, DataBase::class.java, "appdb.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providesCourseDao(
        appDb: DataBase
    ): CourseDao = appDb.courseDao()
}