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

class MainActivity :  BaseActivity<MainContarctor.Presenter>(), MainContarctor.View {

    private var binding : ActivityMainBinding ? =null

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

    override fun isConnectedToInternet() {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
       
    }

    override fun init(savedInstanceState: Bundle?) {
        mainPresenter = MainPresenter(this.application)
        mainPresenter.attach(this)
        mainPresenter.loadMovies()
    }


}