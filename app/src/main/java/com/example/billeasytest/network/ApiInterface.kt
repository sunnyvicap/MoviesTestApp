package com.example.billeasytest.network

import com.example.billeasytest.model.MoviesNowPlaying
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("movie/now_playing?")
    fun getMoviesNowPlaying(@QueryMap map: HashMap<String, String>): Call<MoviesNowPlaying>
}