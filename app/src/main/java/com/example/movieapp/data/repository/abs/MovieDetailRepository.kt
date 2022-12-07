package com.example.movieapp.data.repository.abs

import androidx.annotation.WorkerThread
import com.example.movieapp.data.model.Movie

interface MovieDetailRepository {

    @WorkerThread
    suspend fun getMovieDetails(movieId :String) : Movie
}