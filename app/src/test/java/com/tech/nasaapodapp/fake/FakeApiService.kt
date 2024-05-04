package com.tech.nasaapodapp.fake

import com.tech.nasaapodapp.util.TestUtils
import com.tech.nasaapodapp.nasa_feature.data.remote.NasaApodAPI
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem

class FakeApiService: NasaApodAPI {
    override suspend fun getApod(apiKey: String, date: String?):
            List<NasaApodDataItem> = TestUtils.fakeApodList
}