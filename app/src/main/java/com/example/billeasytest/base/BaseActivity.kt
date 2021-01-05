package com.example.billeasytest.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.billeasytest.ui.MainContarctor
import com.example.billeasytest.util.NetworkStateReceiverListener
import com.example.billeasytest.util.NetworkUtils

abstract class   BaseActivity<T> : AppCompatActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutBinding());
        init(savedInstanceState)
    }

    abstract fun init(savedInstanceState: Bundle?)



    abstract  fun getLayoutBinding(): View



}