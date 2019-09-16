package com.projects.mycompany.apod_app.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Apod(
    val title: String,
    val explanation: String,
    val url: String ):Parcelable