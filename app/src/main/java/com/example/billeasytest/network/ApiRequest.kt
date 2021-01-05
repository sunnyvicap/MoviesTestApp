package com.example.billeasytest.network

import com.example.billeasytest.util.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiRequest {


    var client : OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->

                addInterceptor(chain)
            })
            .build()

    private fun addInterceptor(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Cache-Control", "max-age=640000")
                .method(original.method, original.body)
                .build()

        return chain.proceed(request)

    }


    private val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BAS_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}