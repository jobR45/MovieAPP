package com.example.movieapp.di

import com.bumptech.glide.RequestManager
import com.example.movieapp.ui.home.adapter.ListMovieAdapter
import com.example.movieapp.ui.home.paging.LoaderStateAdapter
import com.example.movieapp.ui.home.paging.MoviePaginationAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {


    @Provides
    fun providesAdapter( @Named("glideAdapter") requestManager: RequestManager) : ListMovieAdapter {
        return  ListMovieAdapter(requestManager)
    }

    @Provides
    fun providesPagingAdapter(@Named("glideAdapter") requestManager: RequestManager) : MoviePaginationAdapter{

        return MoviePaginationAdapter(requestManager)
    }


}