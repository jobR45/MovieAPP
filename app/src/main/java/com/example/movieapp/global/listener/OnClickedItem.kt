package com.example.movieapp.global.listener

interface OnClickedItem<T> {
    fun clickItem(data : T)
}