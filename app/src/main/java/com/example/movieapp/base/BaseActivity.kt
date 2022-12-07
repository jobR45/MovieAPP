package com.example.movieapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.movieapp.global.helper.Navigation

abstract class BaseActivity : AppCompatActivity() {


    protected fun registerBaseObservers(viewModel: ViewModel) {
        if (viewModel is BaseAndroidViewModel) {
            registerNavigation(viewModel)

            //we can dialogs, Toasts...
        }
    }
    private fun registerNavigation(viewModel: BaseAndroidViewModel) {
        viewModel.navigation.observe(this) { navigate(it) }
    }

    open fun navigate(navigateTo: Navigation) {

    }


}