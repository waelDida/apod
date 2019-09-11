package com.projects.mycompany.apod_app.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.mycompany.apod_app.data.Apod

class DetailsViewModel(apod: Apod) : ViewModel(){

    private val _data = MutableLiveData<Apod>()
    val data: LiveData<Apod>
        get() = _data

    init {
        _data.value = apod
    }
}

