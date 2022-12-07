package com.example.movieapp.di

import com.bumptech.glide.RequestManager
import com.example.movieapp.ui.home.adapter.ListMovieAdapter
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

}