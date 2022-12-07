package com.example.movieapp.global.helper

sealed class Navigation {

    object HomeNavigation : Navigation()
    data class DetailNavigation( val  idMovie :Int  ) :Navigation()

}