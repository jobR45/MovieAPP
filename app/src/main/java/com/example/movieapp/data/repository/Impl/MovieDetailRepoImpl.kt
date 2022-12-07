package com.example.movieapp.data.repository.Impl

import com.example.movieapp.base.BaseRepository
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.repository.abs.MovieDetailRepository
import com.example.movieapp.data.retrofit.ApiClient
import javax.inject.Inject

class MovieDetailRepoImpl @Inject constructor(apiClient: ApiClient) :
    BaseRepository(apiClient),MovieDetailRepository {

    override suspend fun getMovieDetails(movieId: String): Movie {
        return  apiClient.getMovieDetails(movieId)
    }

}