package com.projects.mycompany.apod_app.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.projects.mycompany.apod_app.database.getDataBase
import com.projects.mycompany.apod_app.repository.ApodRepository
import retrofit2.HttpException

class RefreshDataWorker(context: Context, params: WorkerParameters): CoroutineWorker(context,params){

    companion object{
        const val WORK_NAME ="refresh_data_worker"
    }
    override suspend fun doWork(): Result {
        val database = getDataBase(applicationContext)
        val repository = ApodRepository(database)
        try{
            repository.refreshDataBase()
            return Result.success()
        }
        catch (e: HttpException){
            return Result.retry()
        }
    }
}