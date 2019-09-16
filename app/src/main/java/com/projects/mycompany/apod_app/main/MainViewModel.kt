package com.projects.mycompany.apod_app.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.projects.mycompany.apod_app.data.Apod
import com.projects.mycompany.apod_app.database.getDataBase
import com.projects.mycompany.apod_app.isOnline
import com.projects.mycompany.apod_app.repository.ApodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch



class MainViewModel(application: Application): AndroidViewModel(application){


    private val _navigateToSelectedApod = MutableLiveData<Apod>()
    val navigateToSelectedApod: LiveData<Apod>
        get() = _navigateToSelectedApod

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = getDataBase(application)
    private val repository = ApodRepository(database)

    val apods = repository.apods

    init {
        if(isOnline(application))
            refreshData()
    }

    private fun refreshData(){
        viewModelScope.launch {
            repository.refreshDataBase()
        }

    }

    fun onNavigate(apod: Apod){
        _navigateToSelectedApod.value = apod
    }

    fun onNvigateTerminated(){
        _navigateToSelectedApod.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}