package com.projects.mycompany.apod_app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.mycompany.apod_app.NASA_KEY
import com.projects.mycompany.apod_app.data.Apod
import com.projects.mycompany.apod_app.network.ApodService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainViewModel: ViewModel(){

    private val _response = MutableLiveData<Apod>()
    val response: LiveData<Apod>
        get() = _response

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _navigate = MutableLiveData<Apod>()
    val navigate: LiveData<Apod>
        get() = _navigate

    init {
      getApod()
    }

    private fun getApod(){
        ApodService.retrofitService.getData(NASA_KEY).enqueue(object: Callback<Apod>{
            override fun onFailure(call: Call<Apod>, t: Throwable) {
                if(t is IOException)
                    _status.value = "No internet connection"
                else
                    _status.value = "Something wrong happened"
            }
            override fun onResponse(call: Call<Apod>, response: Response<Apod>) {
               _response.value = response.body()
            }
        })
    }

    fun onNavigate(){
        _navigate.value = _response.value
    }

    fun onNvigateTerminated(){
        _navigate.value = null
    }
}