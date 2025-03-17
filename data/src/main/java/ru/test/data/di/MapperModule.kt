package ru.test.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.test.data.entity.CourseEntity
import ru.test.data.mapper.CourseMapperImpl
import ru.test.domain.mapper.CourseMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    @Singleton
    abstract fun bindCourseMapper(impl: CourseMapperImpl): CourseMapper<CourseEntity>
}