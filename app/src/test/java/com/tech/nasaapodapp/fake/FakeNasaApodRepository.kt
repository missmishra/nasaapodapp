package com.tech.nasaapodapp.fake

import com.tech.nasaapodapp.util.TestUtils
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import com.tech.nasaapodapp.nasa_feature.domain.repository.NasaApodRepository

class FakeNasaApodRepository: NasaApodRepository {

    private var isApiError = false
    private val agents = TestUtils.fakeApodList

    /**
     * FakeAgentsRepository exclusive API for handling the creation of fake api errors
     */
    fun setIsApiErrorTrue() {
        isApiError = true
    }

    companion object {
        const val EXCEPTION = "There is an api error!"
    }

    override suspend fun getNasaApod(apiKey: String, date: String?): List<NasaApodDataItem> =
        if (!isApiError) {
            agents
        } else {
            throw Exception(EXCEPTION)
        }

}