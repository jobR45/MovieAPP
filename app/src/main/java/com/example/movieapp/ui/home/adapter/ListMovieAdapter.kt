package com.example.movieapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.movieapp.data.model.Movie
import com.example.movieapp.global.listener.DataAdapterListener
import com.example.movieapp.ui.home.HomeViewModel

class ListMovieAdapter (private val requestManager: RequestManager) :
    RecyclerView.Adapter<MovieViewHolder>(),
    DataAdapterListener<List<Movie>>{

    private val listMovie = mutableListOf<Movie>()
    lateinit var viewModel: HomeViewModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent,viewModel,requestManager)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
       return listMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(data: List<Movie>) {
        listMovie.clear()
        listMovie.addAll(data)
        notifyDataSetChanged()
    }

}