package com.example.billeasytest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


class MainActivity : BaseActivity<MainContarctor.Presenter>(), MainContarctor.View {

    private var loadMore: Boolean = false
    private var binding: ActivityMainBinding? = null
    private var movieResult = mutableListOf<Result>()

    private lateinit var adapter: MoviesAdapter
    private lateinit var mainPresenter: MainPresenter

    private var currentPage = 1
    private var totalPages: Int? = 0
    var pastVisiblesItems = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0


    override fun getLayoutBinding(): View {

        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding!!.root;
    }


    override fun showLoading() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding?.progressBar?.visibility = View.GONE

    }


    override fun init(savedInstanceState: Bundle?) {
        mainPresenter = MainPresenter(this.application)
        mainPresenter.attach(this)


        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = RecyclerView.VERTICAL

        binding?.movieRecycler?.layoutManager = mLayoutManager

        adapter = MoviesAdapter(this, movieResult)
        binding?.movieRecycler?.adapter = adapter


        mainPresenter.loadMovies(currentPage)
        mainPresenter.loadResultFromLocal()
        binding?.movieRecycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                if(!loadMore)
                  if (currentPage < totalPages!!) {
                    currentPage++;

                    loadMore = true;


                    mainPresenter.loadMovies(currentPage)
                }
            }


        })


    }


    override fun onMoviesSuccess(@NotNull moviesNowPlaying: MoviesNowPlaying) {


        totalPages = moviesNowPlaying.totalPages


    }

    override fun onMoviesFromLocal(result: List<Result>) {
        if (!loadMore) {

            if (movieResult.isNotEmpty()) {

                movieResult.clear()
            }

        }

        movieResult.addAll(result)
        adapter.notifyDataSetChanged()
    }

    override fun onError(e: Throwable) {

        e.printStackTrace()
    }


}