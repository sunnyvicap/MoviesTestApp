package com.example.billeasytest.ui

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.billeasytest.R
import com.example.billeasytest.base.BasePresenter
import com.example.billeasytest.model.MoviesNowPlaying
import com.example.billeasytest.network.ApiInterface
import com.example.billeasytest.network.ApiRequest
import com.example.billeasytest.util.AppConstants
import com.example.billeasytest.util.NetworkUtils
import com.example.billeasytest.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessControlContext

class MainPresenter(application: Application) : BasePresenter<MainContarctor.View>(), MainContarctor.Presenter {


    val application = application


    override fun loadMovies() {

        if(!NetworkUtils(context = application).isConnected()){

            application.toast(application.resources.getString(R.string.connection_error))
            return
        }
        val map = HashMap<String,String>()
        map["api_key"] = AppConstants.API_KEY
        map["language"] = "en-US"
        map["page"] = "1"

        val callApiClient : Call<MoviesNowPlaying> = ApiRequest.buildService(ApiInterface::class.java).getMoviesNowPlaying(map)

        Log.d("url", callApiClient.request().url.toString())


        getView().showLoading()

        callApiClient.enqueue(object: Callback<MoviesNowPlaying>{
            override fun onResponse(call: Call<MoviesNowPlaying>,
                response: Response<MoviesNowPlaying>) {

                getView().hideLoading()


            }

            override fun onFailure(call: Call<MoviesNowPlaying>, t: Throwable) {

                t.stackTrace
            }

        })
    }


}