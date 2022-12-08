package com.example.movieapp.data.retrofit

import android.content.Context
import com.example.movieapp.global.utils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class EndPointInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!context.isNetworkAvailable()) {
            throw NetworkNotFoundException()
        }
        return chain.proceed(request)
    }

    class NetworkNotFoundException : IOException("No network available")


}