package com.projects.mycompany.apod_app

import android.content.Context
import android.net.ConnectivityManager

const val  NASA_KEY = "axSu9kt37JvZJyD8vQpI6NWD6VLS8Sdcyv8v4jlo"

fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}