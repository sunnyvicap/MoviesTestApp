package com.example.billeasytest.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.billeasytest.ui.MainContarctor

abstract class   BaseActivity<T> : AppCompatActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle??) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutBinding());
        init(savedInstanceState)
    }

    abstract fun init(savedInstanceState: Bundle?)


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    abstract  fun getLayoutBinding(): View

}