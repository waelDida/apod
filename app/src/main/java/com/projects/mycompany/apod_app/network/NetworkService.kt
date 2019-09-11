package com.projects.mycompany.apod_app.network


import com.projects.mycompany.apod_app.data.Apod
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL="https://api.nasa.gov/planetary/"


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface ApodApiService{

    @GET("apod")
    fun getData(@Query("api_key")Type: String): Call<Apod>
}

object ApodService{
    val retrofitService: ApodApiService by lazy {
        retrofit.create(ApodApiService::class.java)
    }
}
