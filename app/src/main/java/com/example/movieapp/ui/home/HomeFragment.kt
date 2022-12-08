package com.example.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.data.retrofit.EndPointInterceptor
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.global.helper.Navigation
import com.example.movieapp.ui.home.adapter.ListMovieAdapter
import com.example.movieapp.ui.home.paging.LoaderStateAdapter
import com.example.movieapp.ui.home.paging.MoviePaginationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var moviePagingAdapter: MoviePaginationAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBaseObserver(viewModel)


        showMoviePagination()

    }

    private fun showMoviePagination() {
        registerMovieAdapter()
        loadStateArtAdapter()
        observePaginationMovies()
        retryMoviePagination()
    }

    private fun observePaginationMovies() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPagingMovie().collectLatest {
                moviePagingAdapter.submitData(it)
            }
        }

    }

    private fun loadStateArtAdapter() {

        moviePagingAdapter.addLoadStateListener { loadState ->

            loadStateVisibility(loadState.refresh)

        }

    }

    private fun loadStateVisibility(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {
                binding.progressHomeFragment.visibility = View.VISIBLE
                binding.tvErrorMovies.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
            }
            is LoadState.Error -> {

                if (loadState.error is EndPointInterceptor.NetworkNotFoundException) {
                    binding.tvErrorMovies.text = activity?.getString(R.string.global_error_internet)
                } else {
                    binding.tvErrorMovies.text = activity?.getString(R.string.global_error_message)
                }
                binding.progressHomeFragment.visibility = View.GONE
                binding.tvErrorMovies.visibility = View.VISIBLE
                binding.btnRetry.visibility = View.VISIBLE
                binding.swipeLayout.visibility = View.GONE

            }
            is LoadState.NotLoading -> {

                binding.progressHomeFragment.visibility = View.GONE
                binding.tvErrorMovies.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
                binding.swipeLayout.visibility = View.VISIBLE
                binding.swipeLayout.isRefreshing = false

            }
        }
    }


    private fun retryMoviePagination() {


        binding.btnRetry.setOnClickListener {
            moviePagingAdapter.retry()
        }

        binding.swipeLayout.setOnRefreshListener {
            moviePagingAdapter.refresh()

        }

    }

    private fun registerMovieAdapter() {
        moviePagingAdapter.viewModel = viewModel
        binding.rvMovie.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvMovie.adapter = moviePagingAdapter.withLoadStateFooter(LoaderStateAdapter(moviePagingAdapter::retry))
    }


    override fun navigate(navigationTo: Navigation) {


        if (navigationTo is Navigation.DetailNavigation) {
            val actionHomeToDetailFragment =
                HomeFragmentDirections.actionNavigationHomeToNavigationDetail(navigationTo.idMovie)
            findNavController()?.navigate(actionHomeToDetailFragment)
        }

    }


}