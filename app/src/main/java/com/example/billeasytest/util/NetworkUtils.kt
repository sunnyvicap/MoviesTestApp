package com.example.billeasytest.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtils(private  val context: Context?) {

    fun isConnected(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        var result = false
        if (activeNetwork != null) {
            result = activeNetwork.isConnectedOrConnecting
        }
        return result
    }

}
