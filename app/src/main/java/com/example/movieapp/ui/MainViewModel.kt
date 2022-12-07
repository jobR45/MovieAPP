package com.example.movieapp.ui

import android.app.Application
import com.example.movieapp.base.BaseAndroidViewModel
import com.example.movieapp.global.helper.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : BaseAndroidViewModel(application) {



}