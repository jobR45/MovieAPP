package com.example.movieapp.ui.home

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.movieapp.base.BaseAndroidViewModel
import com.example.movieapp.data.repository.abs.MovieRepository
import com.example.movieapp.global.helper.Navigation
import com.example.movieapp.global.listener.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val movieRepository: MovieRepository,
    private val schedulerProvider: SchedulerProvider,
    savedStateHandle: SavedStateHandle
) :BaseAndroidViewModel(application){


    fun actionToMovieDetail() {

        navigate(Navigation.DetailNavigation(111)) //random id number for debug
    }

}