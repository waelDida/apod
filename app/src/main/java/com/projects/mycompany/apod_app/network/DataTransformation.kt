package com.projects.mycompany.apod_app.network

import com.projects.mycompany.apod_app.database.DataBaseApod

data class NetworkApod(
    val title: String,
    val explanation: String,
    val url: String
)

fun List<NetworkApod>.asDataBaseModel(): List<DataBaseApod>{
    return map{
        DataBaseApod(title = it.title,explanation = it.explanation,url = it.url)
    }

}