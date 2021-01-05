package com.example.billeasytest.ui

import com.example.billeasytest.base.BaseMvpPresenter
import com.example.billeasytest.base.BaseView
import com.example.billeasytest.model.MoviesNowPlaying

interface MainContarctor {

    interface Presenter : BaseMvpPresenter<MainContarctor.View>{

        fun loadMovies();
    }

    interface View  : BaseView{

        fun onMoviesSuccess(moviesNowPlaying: MoviesNowPlaying)

        fun onError(e : Throwable);

    }
}