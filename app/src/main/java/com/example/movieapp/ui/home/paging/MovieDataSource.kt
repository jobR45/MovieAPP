package com.example.movieapp.ui.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.repository.abs.MovieRepository
import com.example.movieapp.global.listener.SchedulerProvider
import com.example.movieapp.global.utils.DebugLog
import com.example.movieapp.global.utils.FIRST_PAGE
import com.example.movieapp.global.utils.TAG
import kotlinx.coroutines.withContext

class MovieDataSource(
    private val movieRepository: MovieRepository,
    private val schedulerProvider: SchedulerProvider
) : PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: FIRST_PAGE

        return try {

            val list = withContext(schedulerProvider.dispatchersIO()) {
                movieRepository.getPagingMovies(position)

            }
            DebugLog.i(TAG, list.toString())
            LoadResult.Page(
                data = list,
                prevKey = if (position == FIRST_PAGE) null else position.minus(1),
                nextKey = if (list.isEmpty()) null else position.plus(1)
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
