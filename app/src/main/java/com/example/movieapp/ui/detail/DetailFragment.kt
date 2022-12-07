package com.example.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment() {


    private lateinit var binding: FragmentDetailBinding
    private val viewModel : DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentDetailBinding.inflate(inflater,container,false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBaseObserver(viewModel)

        //For debug
        viewModel.movieId.observe(viewLifecycleOwner, Observer {
            binding.tvDetail.text = "Movie ID is  + ${it.toString()}"
        })

    }

}