package com.example.movieapp.global.helper

sealed class LoadingState {
    object Loading : LoadingState()
    data class Loaded(val isEmpty : Boolean) : LoadingState()
    data class Error( val throwable : Throwable) : LoadingState()
}