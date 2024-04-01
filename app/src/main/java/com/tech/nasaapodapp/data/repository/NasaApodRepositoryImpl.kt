package com.tech.nasaapodapp.data.repository

import com.tech.nasaapodapp.data.remote.NasaApodAPI
import com.tech.nasaapodapp.data.remote.dto.NasaApodData
import com.tech.nasaapodapp.data.remote.dto.NasaApodDataItem
import com.tech.nasaapodapp.domain.repository.NasaApodRepository
import timber.log.Timber
import javax.inject.Inject

class NasaApodRepositoryImpl @Inject constructor(private val nasaApodApi: NasaApodAPI): NasaApodRepository {
    override suspend fun getNasaApod(apiKey: String,date: String?): List<NasaApodDataItem> {
        Timber.e("NasaApodRepositoryImpl: ${nasaApodApi.getApod(apiKey,date)}")
        return nasaApodApi.getApod(apiKey,date)
    }
}