package com.example.movieapp.base

import com.example.movieapp.data.retrofit.ApiClient

abstract class BaseRepository (
    protected val apiClient : ApiClient
    )