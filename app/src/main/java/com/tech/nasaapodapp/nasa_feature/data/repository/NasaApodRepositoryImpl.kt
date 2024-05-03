package com.tech.nasaapodapp.nasa_feature.data.repository

import com.tech.nasaapodapp.nasa_feature.data.remote.NasaApodAPI
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import com.tech.nasaapodapp.nasa_feature.domain.repository.NasaApodRepository
import timber.log.Timber
import javax.inject.Inject

class NasaApodRepositoryImpl @Inject constructor(private val nasaApodApi: NasaApodAPI): NasaApodRepository {
    override suspend fun getNasaApod(apiKey: String,date: String?): List<NasaApodDataItem> {
        Timber.e("NasaApodRepositoryImpl: ${nasaApodApi.getApod(apiKey,date)}")
        return nasaApodApi.getApod(apiKey,date)
    }
}