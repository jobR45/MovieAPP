package com.example.movieapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.base.BaseActivity
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.global.helper.Navigation
import com.example.movieapp.ui.detail.DetailFragment
import com.example.movieapp.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var currentFragment: String? = null
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //init navController
        navController =
            androidx.navigation.Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.setGraph(R.navigation.mobile_navigation, intent.extras)


        registerBindingAndBaseObservers(binding)

    }

    /**
     * Register the UI for XMLBinding
     * Register the activity for base observers
     */
    private fun registerBindingAndBaseObservers(binding: ActivityMainBinding) {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        registerBaseObservers(viewModel)
    }


    override fun navigate(navigateTo: Navigation) {

        when (navigateTo) {

            is Navigation.HomeNavigation ->changeCurrentFragment(HomeFragment(),R.id.navigation_home)
            is Navigation.DetailNavigation ->changeCurrentFragment(DetailFragment(),R.id.navigation_detail)


        }
    }
    private fun changeCurrentFragment(fragment: Fragment, id :Int){
        if (currentFragment?.equals(fragment::class.toString()) == true) return
        currentFragment = fragment::class.toString()
        navController.navigate(id)
    }








}
