package com.projects.mycompany.apod_app.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.mycompany.apod_app.data.Apod
import java.lang.IllegalArgumentException

class DetailsViewModelFactory(private val apod: Apod): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsViewModel::class.java))
            return DetailsViewModel(apod) as T
        throw IllegalArgumentException("Unknown viewModel class")
    }

}