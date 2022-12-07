package com.example.movieapp.global.helper

sealed class LoadingState {
    object Loading : LoadingState()
    object Loaded : LoadingState()
    data class Error( val throwable : Throwable) : LoadingState()
}