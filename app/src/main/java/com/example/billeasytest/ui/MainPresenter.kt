package com.example.billeasytest.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.room.withTransaction
import com.example.billeasytest.R
import com.example.billeasytest.base.BasePresenter
import com.example.billeasytest.db.MoviesDatabase
import com.example.billeasytest.model.MoviesNowPlaying
import com.example.billeasytest.network.ApiInterface
import com.example.billeasytest.network.ApiRequest
import com.example.billeasytest.util.AppConstants
import com.example.billeasytest.util.NetworkUtils
import com.example.billeasytest.util.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(application: Application) : BasePresenter<MainContarctor.View>(), MainContarctor.Presenter {


    val application = application
    val db = MoviesDatabase.getInstance(application)
    val coroutineScope :CoroutineScope = CoroutineScope(Dispatchers.IO)




    override fun loadMovies(pagekey : Int) {

        if(!NetworkUtils(context = application).isConnected()){

            application.toast(application.resources.getString(R.string.connection_error))
            return
        }
        val map = HashMap<String,Any>()
        map["api_key"] = AppConstants.API_KEY
        map["language"] = "en-US"
        map["page"] = pagekey

        val callApiClient : Call<MoviesNowPlaying> = ApiRequest.buildService(ApiInterface::class.java).getMoviesNowPlaying(map)


        getView().showLoading()

        callApiClient.enqueue(object: Callback<MoviesNowPlaying>{
            override fun onResponse(call: Call<MoviesNowPlaying>,
                response: Response<MoviesNowPlaying>) {

                getView().hideLoading()

              //  getView().onMoviesSuccess(response.body()!!)

                coroutineScope.async {

                   saveDataToLocal(response.body()!!)
               }



            }

            override fun onFailure(call: Call<MoviesNowPlaying>, t: Throwable) {

                t.stackTrace
            }

        })

    }

    override fun loadResultFromLocal() {
        coroutineScope.async {
            db.reposDao().getAllMovies().collect {

                getView().onMoviesFromLocal(it)

            }
        }

    }

    suspend fun saveDataToLocal(body: MoviesNowPlaying) {
         db.reposDao().insertMovies(body.results)
         Log.e("Data", "inserted")
    }


}