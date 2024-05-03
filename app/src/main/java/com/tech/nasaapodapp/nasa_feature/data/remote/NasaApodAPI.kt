package com.tech.nasaapodapp.nasa_feature.data.remote

import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApodAPI {

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String,
        @Query("start_date") date: String? = null
    ): List<NasaApodDataItem>

}