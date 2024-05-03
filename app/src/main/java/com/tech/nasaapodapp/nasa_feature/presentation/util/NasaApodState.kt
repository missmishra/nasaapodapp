package com.tech.nasaapodapp.nasa_feature.presentation.util

import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem

sealed interface NasaApodState {
    data object Loading : NasaApodState
    data class Success(val nasaApodData: List<NasaApodDataItem>?) : NasaApodState

    data class Error(val message: String) : NasaApodState

}
