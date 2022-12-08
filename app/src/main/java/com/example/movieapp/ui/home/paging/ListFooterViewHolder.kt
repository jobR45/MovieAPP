package com.example.movieapp.ui.home.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FooterListMovieBinding
import com.example.movieapp.global.helper.LoadingState
import com.example.movieapp.ui.home.HomeViewModel

class ListFooterViewHolder(
    private val binding: FooterListMovieBinding,
    private val retry: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind (loadState: LoadState) {
        binding.root.setOnClickListener {
            retry.invoke()
        }
        if (loadState is LoadState.Error) {
            binding.tvErrorFooterMovie.text = loadState.error.localizedMessage
        }
        binding.isLoading = loadState is LoadState.Loading
        binding.isError = loadState !is LoadState.Loading
        binding.executePendingBindings()
    }


    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ListFooterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FooterListMovieBinding.inflate(inflater, parent, false)
            return ListFooterViewHolder(binding, retry)
        }
    }
}