package com.example.billeasytest.ui

import com.example.billeasytest.base.BaseMvpPresenter
import com.example.billeasytest.base.BaseView
import com.example.billeasytest.model.MoviesNowPlaying
import com.example.billeasytest.model.Result

interface MainContarctor {

    interface Presenter : BaseMvpPresenter<MainContarctor.View>{

        fun loadMovies(pagekey : Int)

        fun loadResultFromLocal();
    }

    interface View  : BaseView{

        fun onMoviesSuccess(moviesNowPlaying: MoviesNowPlaying)

        fun onMoviesFromLocal(result: List<Result>)

        fun onError(e : Throwable);

    }
}