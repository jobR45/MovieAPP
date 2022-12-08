package com.example.movieapp.data.repository.Impl

import com.example.movieapp.base.BaseRepository
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.repository.abs.MovieRepository
import com.example.movieapp.data.retrofit.ApiClient
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    apiClient: ApiClient
) : BaseRepository (apiClient), MovieRepository{


    override suspend fun getAllMovies(): List<Movie> {
        val response = apiClient.getAllMovies()
        return response.results
    }

    override suspend fun getPagingMovies(page :Int): List<Movie> {
        val response = apiClient.getPagingMovies(page)
        return response.results
    }


}