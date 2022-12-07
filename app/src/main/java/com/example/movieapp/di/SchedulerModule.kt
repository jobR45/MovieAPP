package com.example.movieapp.di

import com.example.movieapp.global.helper.AppSchedulerProvider
import com.example.movieapp.global.listener.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SchedulerModule {

    @Provides
    @Singleton
    fun providesDispatchers() : SchedulerProvider {
        return AppSchedulerProvider()
    }
}