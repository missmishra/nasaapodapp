package com.tech.nasaapodapp.nasa_feature.domain.repository

import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem

interface NasaApodRepository {

    suspend fun getNasaApod(apiKey: String,date: String? = null): List<NasaApodDataItem>
}