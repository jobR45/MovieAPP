package com.example.movieapp.di

import com.example.movieapp.data.repository.Impl.MovieDetailRepoImpl
import com.example.movieapp.data.repository.Impl.MovieRepositoryImpl
import com.example.movieapp.data.repository.abs.MovieDetailRepository
import com.example.movieapp.data.repository.abs.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesMovieRepository(movieRepositoryImpl: MovieRepositoryImpl) : MovieRepository

    @Binds
    abstract fun providesMovieDetailRepo(movieDetailRepoImpl: MovieDetailRepoImpl) : MovieDetailRepository
}