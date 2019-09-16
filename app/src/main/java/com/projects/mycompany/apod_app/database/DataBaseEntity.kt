package com.projects.mycompany.apod_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.projects.mycompany.apod_app.data.Apod


@Entity
data class DataBaseApod(
    val title: String,
    val explanation: String,
    @PrimaryKey val url: String
)

fun List<DataBaseApod>.asDomainModel(): List<Apod>{
    return map{
        Apod(title = it.title, explanation = it.explanation, url = it.url )
    }

}