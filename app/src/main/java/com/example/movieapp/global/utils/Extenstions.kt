package com.example.movieapp.global.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.movieapp.BuildConfig
import com.example.movieapp.global.listener.DataAdapterListener
import java.util.concurrent.CancellationException


val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun Context?.isNetworkAvailable(): Boolean {
    if (this == null) return false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}




@BindingAdapter("data")
fun <T> initializeAdapter(recyclerView: RecyclerView, data : T?){

    data?.let {
        if (recyclerView.adapter is DataAdapterListener<*>){

            (recyclerView.adapter as DataAdapterListener<T>).setData(data)
        }
    }
}



@BindingAdapter(value= ["imageUrl","placeholder","requestManager"],requireAll = true)
fun setImage(view : ImageView, imageUrl:String?, placeHolder : Drawable, requestManager: RequestManager){
    if(!imageUrl.isNullOrEmpty()){
        requestManager
            .load(BuildConfig.BASE_IMAGE_URL + imageUrl)
            .into(view)
    }
    else{
        view.setImageDrawable(placeHolder)
    }

}




/**
 * Custom try catch that handles Cancellation exception*/
suspend fun tryCatch(
    tryBlock: suspend () -> Unit,
    catchBlock: suspend (Throwable) -> Unit,
    handleCancellationExceptionManually: Boolean = false
) {
    try {
        tryBlock()
    } catch (e: Throwable) {
        if (e !is CancellationException ||
            handleCancellationExceptionManually
        ) {
            catchBlock(e)
        } else {
            throw e
        }
    }
}