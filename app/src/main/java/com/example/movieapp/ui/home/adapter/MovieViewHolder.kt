package com.example.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.RowListMovieBinding
import com.example.movieapp.ui.home.HomeViewModel

class MovieViewHolder(
    private val binding : RowListMovieBinding,
    private val viewModel : HomeViewModel,
    private val requestManager: RequestManager
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie :Movie?){

        //add value and click listener
        binding.movie = movie
        binding.requestManager = requestManager
        binding.root.setOnClickListener {
            if (movie != null) {
                viewModel.clickItem(movie)
            }
        }

        binding.executePendingBindings()
    }

    companion object{
        fun create(parent: ViewGroup,viewModel: HomeViewModel,requestManager: RequestManager) : MovieViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = RowListMovieBinding.inflate(inflater,parent,false)
            return MovieViewHolder(
                binding,
                viewModel,
                requestManager
            )
        }
    }
}