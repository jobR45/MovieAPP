package com.example.movieapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.movieapp.data.repository.abs.MovieDetailRepository
import com.example.movieapp.global.listener.SchedulerProvider
import com.example.movieapp.global.utils.ExtraKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    movieDetailRepository: MovieDetailRepository,
    private val schedulerProvider: SchedulerProvider,
    savedStateHandle: SavedStateHandle
) :AndroidViewModel(application){


    /** Debug
     *  retrieve movie Id argument from advanced Home fragment*/
    private val movieIdArg = savedStateHandle.get<Int>(ExtraKeys.MovieDetail.MOVIE_ID)

     val movieId = MutableLiveData<Int?>()

    init {
        movieId.value = movieIdArg
    }
}