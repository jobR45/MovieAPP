package com.example.movieapp.data.retrofit



import com.example.movieapp.BuildConfig
import com.example.movieapp.BuildConfig.API_KEY
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {


    /**
     * Get with default values */

    @GET("discover/movie?&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate&api_key=${BuildConfig.API_KEY}")
    suspend fun getAllMovies(): MovieResponse

    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getMovieDetails(@Path("movie_id") movieId : String) : Movie

}