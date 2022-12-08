package com.example.movieapp.ui.home

import android.app.Application
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.base.BaseAndroidViewModel
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.repository.abs.MovieRepository
import com.example.movieapp.global.helper.LoadingState
import com.example.movieapp.global.helper.Navigation
import com.example.movieapp.global.listener.OnClickedItem
import com.example.movieapp.global.listener.SchedulerProvider
import com.example.movieapp.global.utils.tryCatch
import com.example.movieapp.ui.home.paging.MovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val movieRepository: MovieRepository,
    private val schedulerProvider: SchedulerProvider
) :BaseAndroidViewModel(application),OnClickedItem<Movie>{





    fun getPagingMovie(): Flow<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { MovieDataSource(movieRepository, schedulerProvider) }
        ).flow.flowOn(schedulerProvider.dispatchersIO()).cachedIn(viewModelScope)

    }


    override fun clickItem(data: Movie) {
        navigate(Navigation.DetailNavigation(data.id))
    }


    /**
     * Code for simple list of movies using simple adapter and livedata
     * Branch --main
     * */
    /*
    val listMovie = MutableLiveData<List<Movie>>()

    private val loadingState = MutableLiveData<LoadingState>()

    val isLoading = loadingState.map {
        it is LoadingState.Loading
    }

    val errorMessage : LiveData<String> = loadingState.map {
        if (it is LoadingState.Error){

            // function from BaseAndroidViewModel
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

     fun getAllMovies(){

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

    }*/

}