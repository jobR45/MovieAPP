package com.example.movieapp.data.repository.abs

import androidx.annotation.WorkerThread
import com.example.movieapp.data.model.Movie

interface MovieRepository {

    @WorkerThread
    suspend fun getAllMovies(): List<Movie>

    @WorkerThread
    suspend fun getPagingMovies(page : Int): List<Movie>

}