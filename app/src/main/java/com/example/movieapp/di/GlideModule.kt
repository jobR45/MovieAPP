package com.example.movieapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlideModule {

    @Provides
    @Named("glideAdapter")
    fun providesGlideAdapter(context : Context)  : RequestManager {
        return Glide.with(context)

    }
    @Provides
    @Singleton
    @Named("glideFragment")
    fun providesGlideFragment(context : Context) :RequestManager{
        return Glide.with(context)

    }
}