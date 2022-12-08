package com.example.movieapp.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.movieapp.R
import com.example.movieapp.base.BaseAndroidViewModel
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.repository.abs.MovieDetailRepository
import com.example.movieapp.global.helper.LoadingState
import com.example.movieapp.global.listener.SchedulerProvider
import com.example.movieapp.global.utils.ExtraKeys
import com.example.movieapp.global.utils.tryCatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val movieDetailRepository: MovieDetailRepository,
    private val schedulerProvider: SchedulerProvider,
    savedStateHandle: SavedStateHandle
) : BaseAndroidViewModel(application) {


    /**
     *  retrieve movie Id argument from advanced Home fragment*/
    private val movieIdArg = savedStateHandle.get<Int>(ExtraKeys.MovieDetail.MOVIE_ID)

    val movieId = MutableLiveData<String>()
    val movie = MutableLiveData<Movie>()
    val loadingState = MutableLiveData<LoadingState>()


    val errorMessage: LiveData<String> = loadingState.map {
        if (it is LoadingState.Error) {

            // function from BaseAndroidViewModel
            handleThrowableText(it.throwable)
        } else ""
    }

    val isLoading = loadingState.map {
        it is LoadingState.Loading
    }

    val isError = errorMessage.map {

        //if the error message is not empty (ie: no error)
        it.isEmpty().not()
    }


    init {

        //for retry btn
        movieId.value = movieIdArg.toString()

        getMovie(movieIdArg.toString())


    }


     fun getMovie(movieId: String) {

        viewModelScope.launch(schedulerProvider.dispatchersUI()) {

            loadingState.value = LoadingState.Loading
            tryCatch({



                val res = withContext(schedulerProvider.dispatchersIO()) {
                    movieDetailRepository.getMovieDetails(movieId)
                }
                loadingState.value = LoadingState.Loaded
                movie.value = res

            }, {

                loadingState.value = LoadingState.Error(it)
            })


        }
    }
}