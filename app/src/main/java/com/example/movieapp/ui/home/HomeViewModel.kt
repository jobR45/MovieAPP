package com.example.movieapp.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.movieapp.base.BaseAndroidViewModel
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.repository.abs.MovieRepository
import com.example.movieapp.global.helper.LoadingState
import com.example.movieapp.global.helper.Navigation
import com.example.movieapp.global.listener.OnClickedItem
import com.example.movieapp.global.listener.SchedulerProvider
import com.example.movieapp.global.utils.tryCatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val movieRepository: MovieRepository,
    private val schedulerProvider: SchedulerProvider
) :BaseAndroidViewModel(application),OnClickedItem<Movie>{


    val listMovie = MutableLiveData<List<Movie>>()

    private val loadingState = MutableLiveData<LoadingState>()

    val isLoading = loadingState.map {
        it is LoadingState.Loading
    }

    val errorMessage : LiveData<String> = loadingState.map {
        if (it is LoadingState.Error){

            // fun from BaseAndroidViewModel
            handleThrowableText(it.throwable)
        }
        else ""
    }

    val isError = errorMessage.map {

        //if the error message is not empty (ie: no error)
        it.isEmpty().not()
    }

    init {
        getAllMovies()
    }

    private fun getAllMovies(){

        viewModelScope.launch (schedulerProvider.dispatchersUI()){

            loadingState.value = LoadingState.Loading
            tryCatch( {
                val res = withContext(schedulerProvider.dispatchersIO()){
                    movieRepository.getAllMovies()
                }

                loadingState.value = LoadingState.Loaded
                listMovie.value = res

            } ,{ error ->   loadingState.value = LoadingState.Error(error)

            })

        }

    }


    override fun clickItem(data: Movie) {
        navigate(Navigation.DetailNavigation(data.id))
    }

}