package com.projects.mycompany.apod_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.projects.mycompany.apod_app.NASA_KEY
import com.projects.mycompany.apod_app.data.Apod
import com.projects.mycompany.apod_app.database.ApodDataBase
import com.projects.mycompany.apod_app.database.asDomainModel
import com.projects.mycompany.apod_app.network.ApodService
import com.projects.mycompany.apod_app.network.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApodRepository(private val database: ApodDataBase ){

    val apods : LiveData<List<Apod>> = Transformations.map(database.apodDao.getAllApods()){
        it.asDomainModel()
    }

    suspend fun refreshDataBase(){
        withContext(Dispatchers.IO){
            val apod = ApodService.retrofitService.getData(NASA_KEY).await()
            database.apodDao.insert(apod.asDataBaseModel())
        }
    }

}