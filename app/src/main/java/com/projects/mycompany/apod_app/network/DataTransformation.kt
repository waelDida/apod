package com.projects.mycompany.apod_app.network


import com.projects.mycompany.apod_app.database.DataBaseApod

data class NetworkApod(
    val title: String,
    val explanation: String,
    val url: String
)

fun NetworkApod.asDataBaseModel(): DataBaseApod {
    return let {
        DataBaseApod(title = it.title, explanation = it.explanation, url = it.url)
    }
}