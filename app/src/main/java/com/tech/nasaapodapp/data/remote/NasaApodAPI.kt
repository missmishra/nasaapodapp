package com.tech.nasaapodapp.data.remote

import com.tech.nasaapodapp.data.remote.dto.NasaApodData
import com.tech.nasaapodapp.data.remote.dto.NasaApodDataItem
import com.tech.nasaapodapp.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApodAPI {

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String,
        @Query("start_date") date: String? = null
    ): List<NasaApodDataItem>

}