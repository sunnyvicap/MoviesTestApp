package com.example.billeasytest.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.billeasytest.R
import com.example.billeasytest.base.BaseActivity
import com.example.billeasytest.databinding.ActivityMainBinding
import com.example.billeasytest.model.MoviesNowPlaying
import com.example.billeasytest.model.Result
import com.example.billeasytest.ui.adpter.MoviesAdapter
import org.jetbrains.annotations.NotNull

class MainActivity :  BaseActivity<MainContarctor.Presenter>(), MainContarctor.View {

    private var binding : ActivityMainBinding ? =null
    private var movieResult: List<Result> ?= null

    private lateinit var adapter: MoviesAdapter
    private lateinit var mainPresenter: MainPresenter



    override fun getLayoutBinding(): View {

        binding =  ActivityMainBinding.inflate(layoutInflater)
        return binding!!.root;
    }




    override fun showLoading() {
        binding?.progressBar?.visibility= View.VISIBLE
    }

    override fun hideLoading() {
        binding?.progressBar?.visibility= View.GONE

    }



    override fun init(savedInstanceState: Bundle?) {
        mainPresenter = MainPresenter(this.application)
        mainPresenter.attach(this)

        movieResult = ArrayList<Result>()
        mainPresenter.loadMovies()
    }


    override fun onMoviesSuccess(@NotNull moviesNowPlaying: MoviesNowPlaying) {

        movieResult = moviesNowPlaying.results
        adapter = MoviesAdapter(this, movieResult!!)

        binding?.movieRecycler?.adapter = adapter


    }

    override fun onError(e: Throwable) {

        e.printStackTrace()
    }


}