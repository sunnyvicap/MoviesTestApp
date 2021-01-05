package com.example.billeasytest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.billeasytest.R
import com.example.billeasytest.base.BaseActivity
import com.example.billeasytest.databinding.ActivityMainBinding

class MainActivity :  BaseActivity<MainContarctor.Presenter>(), MainContarctor.View {

    private var binding : ActivityMainBinding ? =null;

    override fun getLayoutBinding(): View {

        binding =  ActivityMainBinding.inflate(layoutInflater)
        return binding!!.root;
    }


    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun init(savedInstanceState: Bundle?) {
    }


}