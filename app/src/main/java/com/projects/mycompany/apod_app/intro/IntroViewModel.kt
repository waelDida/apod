package com.projects.mycompany.apod_app.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntroViewModel: ViewModel(){

    private var _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    fun navigate(){
        _navigate.value = true
    }

    fun navigateTerminated(){
        _navigate.value = false
    }


}