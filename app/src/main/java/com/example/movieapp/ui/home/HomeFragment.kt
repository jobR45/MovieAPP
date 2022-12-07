package com.example.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.global.helper.Navigation
import com.example.movieapp.ui.home.adapter.ListMovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()


    @Inject
    lateinit var listMovieAdapter: ListMovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBaseObserver(viewModel)
        registerRecycler()

    }




    private fun registerRecycler(){
        listMovieAdapter.viewModel = viewModel
        binding.rvMovie.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        binding.rvMovie.adapter = listMovieAdapter

    }
    override fun navigate(navigationTo: Navigation) {


          if (navigationTo  is Navigation.DetailNavigation) {
                val actionHomeToDetailFragment =
                    HomeFragmentDirections.actionNavigationHomeToNavigationDetail(navigationTo.idMovie)
                findNavController()?.navigate(actionHomeToDetailFragment)
            }

    }



}