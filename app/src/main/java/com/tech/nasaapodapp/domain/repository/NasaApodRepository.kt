package com.tech.nasaapodapp.domain.repository

import com.tech.nasaapodapp.data.remote.dto.NasaApodData
import com.tech.nasaapodapp.data.remote.dto.NasaApodDataItem

interface NasaApodRepository {

    suspend fun getNasaApod(apiKey: String,date: String? = null): List<NasaApodDataItem>
}