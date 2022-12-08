package com.example.movieapp.ui.home.paging

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.movieapp.data.model.Movie
import com.example.movieapp.ui.home.HomeViewModel
import com.example.movieapp.ui.home.adapter.MovieViewHolder

class MoviePaginationAdapter(private val requestManager: RequestManager)
    : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(artsDiffCallback)
{
    lateinit var viewModel: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return  MovieViewHolder.create(parent,viewModel,requestManager)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MovieViewHolder).bind(getItem(position))
    }


    companion object {
        val artsDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id== newItem.id

            }
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }


}