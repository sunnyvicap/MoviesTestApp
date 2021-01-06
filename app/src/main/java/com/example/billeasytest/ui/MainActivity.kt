package com.example.billeasytest.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.billeasytest.R
import com.example.billeasytest.base.BaseActivity
import com.example.billeasytest.databinding.ActivityMainBinding
import com.example.billeasytest.db.MoviesDatabase
import com.example.billeasytest.model.MoviesNowPlaying
import com.example.billeasytest.model.Result
import com.example.billeasytest.ui.adpter.MoviesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import org.jetbrains.annotations.NotNull

class MainActivity :  BaseActivity<MainContarctor.Presenter>(), MainContarctor.View {

    private var loadMore: Boolean= false
    private var binding : ActivityMainBinding ? =null
    private var movieResult = mutableListOf<Result>()

    private lateinit var adapter: MoviesAdapter
    private lateinit var mainPresenter: MainPresenter

    private var currentPage = 1
    private var totalPages : Int? = 0;

    private val coroutineScope :CoroutineScope = CoroutineScope(Dispatchers.IO)


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


        mainPresenter.loadMovies(currentPage)

        adapter = MoviesAdapter(this, movieResult)
        binding?.movieRecycler?.adapter = adapter

        binding?.movieRecycler?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (currentPage < totalPages!!) {
                    currentPage++;

                    loadMore = true;


                    mainPresenter.loadMovies(currentPage)
                }

            }
        })

       val db = MoviesDatabase.getInstance(this)
        coroutineScope.async {
            db.reposDao().getAllMovies().collect {

                if(!loadMore) {

                    if(movieResult.isNotEmpty()){

                        movieResult.clear()
                    }
                }

                movieResult.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }

    }


    override fun onMoviesSuccess(@NotNull moviesNowPlaying: MoviesNowPlaying) {


        totalPages = moviesNowPlaying.totalPages



    }

    override fun onError(e: Throwable) {

        e.printStackTrace()
    }


}