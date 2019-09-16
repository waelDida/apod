package com.projects.mycompany.apod_app.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ApodDao{

    @Insert
    fun insert(dataBaseApod: DataBaseApod)

    @Query("SELECT * FROM databaseapod")
    fun getAllApods(): LiveData<List<DataBaseApod>>

    @Query("SELECT * FROM databaseapod WHERE title == :title")
    fun getApodByTitle(title: String): DataBaseApod

}

@Database(entities = [DataBaseApod::class], version = 1 )
abstract class ApodDataBase : RoomDatabase(){
    abstract val apodDao: ApodDao
}

private lateinit var INSTANCE: ApodDataBase
fun getDataBase(context: Context):ApodDataBase{
    synchronized(ApodDataBase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context,ApodDataBase::class.java,"apod_data_base").build()
        }
    }
    return INSTANCE
}


