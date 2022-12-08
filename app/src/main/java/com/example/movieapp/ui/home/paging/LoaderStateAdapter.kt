package com.example.movieapp.ui.home.paging


import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

import com.example.movieapp.ui.home.HomeViewModel

class LoaderStateAdapter(  private val retry: () -> Unit):  LoadStateAdapter<ListFooterViewHolder>(){

    lateinit var viewModel: HomeViewModel
    override fun onBindViewHolder(holder: ListFooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ListFooterViewHolder {
        return ListFooterViewHolder.create(parent,retry)
    }


}